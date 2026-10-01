"""Material Gradle packaging regressions using the shared archive fixtures."""

import os
from pathlib import Path
import shutil
import subprocess
import sys
import tarfile
import unittest

REPOSITORY = Path(__file__).resolve().parents[1]
SHARED = REPOSITORY.parent / "megamek" / "gradle"
sys.path.insert(0, str(SHARED))
import test_suite_archive_verifier as fixtures
import suite_archive_verifier as verifier


class CompanionPackagingTests(unittest.TestCase):
    def setUp(self):
        self.fixture = fixtures.SuiteArchiveTests()
        self.fixture.setUp()
        self.addCleanup(self.fixture.doCleanups)
        self.base = self.fixture.base
        self.root = self.base / "megameklab"
        self.root.mkdir()
        self.mm = self.base / "megamek"
        (self.mm / "gradle").mkdir(parents=True)
        for name in ("suite_archive_adapter.gradle", "suite_archive_verifier.py",
                     "suite_data_rules.json"):
            shutil.copy2(SHARED / name, self.mm / "gradle" / name)
        (self.mm / "settings.gradle").write_text(
            "rootProject.name = 'megamek'\ninclude 'megamek'\n", encoding="utf-8")
        self.built = self.mm / "megamek" / "build" / "libs" / "MegaMek.jar"
        self.built.parent.mkdir(parents=True)
        self.rebuilt_bytes = fixtures.zipped({
            "Version.properties": b"major=0\nminor=51\npatch=01\nrevision=\n",
            "META-INF/MANIFEST.MF":
                b"Manifest-Version: 1.0\r\nBuild-Date: rebuilt\r\n\r\n",
        })
        self.built.write_bytes(self.rebuilt_bytes)
        (self.mm / "megamek" / "rebuilt.jar").write_bytes(self.rebuilt_bytes)
        (self.mm / "megamek" / "build.gradle").write_text(
            "tasks.register('jar', Copy) {\n"
            "    from 'rebuilt.jar'\n"
            "    into layout.buildDirectory.dir('libs')\n"
            "    rename 'rebuilt.jar', 'MegaMek.jar'\n"
            "}\n", encoding="utf-8")
        (self.root / "settings.gradle").write_text(
            "rootProject.name = 'MegaMekLabRoot'\n"
            "includeBuild '../megamek'\n", encoding="utf-8")
        primary = self.root / "build" / "libs" / "MegaMekLab.jar"
        primary.parent.mkdir(parents=True)
        primary.write_bytes(self.fixture.jar["MegaMekLab"])
        for name, content in self.fixture.entries("MegaMekLab").items():
            if name == "lib/MegaMek.jar":
                continue
            target = self.root / "payload" / name
            target.parent.mkdir(parents=True, exist_ok=True)
            target.write_bytes(content)
        (self.root / "other.jar").write_bytes(fixtures.zipped({"resource": b"other"}))
        (self.root / "build.gradle").write_text("""
plugins { id 'application' }
version = '0.51.02'
ext.mmDir = rootProject.file('../megamek').absolutePath
apply from: file('../megamek/gradle/suite_archive_adapter.gradle')
application {
    mainClass = 'Fixture'
    applicationName = 'MegaMekLab'
}
dependencies {
    runtimeOnly files('../megamek/megamek/build/libs/MegaMek.jar', 'other.jar')
}
tasks.named('jar') {
    archiveFileName = 'MegaMekLab.jar'
    enabled = false
}
tasks.register('stageFiles')
apply from: file(rootProject.property('companionScript'))
distributions.main {
    distributionBaseName = 'MegaMekLab'
    contents {
        from 'payload'
        duplicatesStrategy = 'exclude'
    }
}
tasks.named('distTar') {
    dependsOn tasks.named('stageFiles')
    archiveExtension = 'tar.gz'
    compression = Compression.GZIP
}
tasks.register('verifyExternal') {
    doLast {
        verifySuiteArchiveShared('MegaMekLab',
                file(rootProject.property('suiteArchiveFile')))
    }
}
""", encoding="utf-8")
        self.fixture.write("MegaMek", self.fixture.entries(
            "MegaMek", older_successors=True))
        self.extracted = self.root / "build" / "suite" / "companions" / "MegaMek.jar"
        self.tar = self.root / "build" / "distributions" / "MegaMekLab-0.51.02.tar.gz"

    def run_gradle(self, task="distTar", *, suite=True, companion=True,
                   extra=(), succeeds=True, project=None):
        wrapper = REPOSITORY / ("gradlew.bat" if os.name == "nt" else "gradlew")
        command = [str(wrapper), "-p", str(project or self.root), task, "--console=plain",
                   f"-PcompanionScript={REPOSITORY / 'gradle' / 'suite_megamek_companion.gradle'}",
                   f"-PsuitePythonExecutable={sys.executable}"]
        if suite:
            command += ["-PsuiteReleaseVersion=0.51.03",
                        "-PsuiteMegaMekVersion=0.51.01",
                        "-PsuiteMegaMekLabVersion=0.51.02",
                        "-PsuiteMekHQVersion=0.51.03"]
            for option, field in (
                ("suiteMegaMekCommit", "megamekCommit"),
                ("suiteMegaMekLabCommit", "megameklabCommit"),
                ("suiteMekHQCommit", "mekhqCommit"),
                ("suiteMmDataCommit", "mmDataCommit"),
            ):
                command.append(f"-P{option}={self.fixture.pins[field]}")
        if companion:
            command.append(f"-PsuiteMegaMekArchiveFile={self.fixture.archives['MegaMek']}")
        result = subprocess.run(command + list(extra), cwd=REPOSITORY,
                                text=True, capture_output=True, timeout=180)
        output = result.stdout + result.stderr
        if succeeds:
            self.assertEqual(result.returncode, 0, output)
        else:
            self.assertNotEqual(result.returncode, 0, output)
        return output

    def packaged_jar(self):
        with tarfile.open(self.tar) as archive:
            member = archive.extractfile("MegaMekLab-0.51.02/lib/MegaMek.jar")
            self.assertIsNotNone(member)
            content = member.read()
            self.assertIsNotNone(archive.getmember("MegaMekLab-0.51.02/lib/other.jar"))
            return content

    def test_supplied_archive_wins_over_rebuilt_runtime_and_stale_extraction(self):
        self.extracted.parent.mkdir(parents=True)
        self.extracted.write_bytes(self.rebuilt_bytes)
        output = self.run_gradle()
        self.assertIn(":prepareSuiteMegaMekCompanion", output)
        self.assertEqual(self.packaged_jar(), self.fixture.jar["MegaMek"])
        self.assertEqual(self.extracted.read_bytes(), self.fixture.jar["MegaMek"])
        self.assertEqual(self.built.read_bytes(), self.rebuilt_bytes)
        verifier.verify(self.tar, "MegaMekLab", fixtures.VERSIONS["MegaMekLab"],
                        self.fixture.pins,
                        {"megamekVersion": fixtures.VERSIONS["MegaMek"]},
                        self.fixture.data,
                        {"MegaMek": self.fixture.archives["MegaMek"]},
                        {"MegaMek": self.extracted,
                         "MegaMekLab": self.root / "build" / "libs" / "MegaMekLab.jar"})

    def test_standalone_suite_build_keeps_sibling_jar_producer(self):
        output = self.run_gradle(companion=False)
        self.assertIn(":megamek:megamek:jar", output)
        self.assertEqual(self.packaged_jar(), self.rebuilt_bytes)
        self.assertFalse(self.extracted.exists())

    def test_install_distribution_prepares_companion_without_staging_task(self):
        output = self.run_gradle("installDist")
        self.assertIn(":prepareSuiteMegaMekCompanion", output)
        self.assertNotIn(":stageFiles", output)
        installed = self.root / "build" / "install" / "MegaMekLab" / "lib"
        self.assertEqual((installed / "MegaMek.jar").read_bytes(),
                         self.fixture.jar["MegaMek"])
        self.assertTrue((installed / "other.jar").is_file())

    def test_normal_build_keeps_sibling_jar(self):
        output = self.run_gradle(suite=False, companion=False)
        self.assertNotIn("prepareSuiteMegaMekCompanion", output)
        self.assertEqual(self.packaged_jar(), self.rebuilt_bytes)
        self.assertFalse(self.extracted.exists())

    def test_invalid_companions_fail_without_falling_back_to_stale_jar(self):
        original = self.fixture.entries("MegaMek", older_successors=True)
        variants = []
        for field, value in (("megamekCommit", "f" * 40),
                             ("mmDataCommit", "f" * 40), ("version", "0.51.00")):
            files = dict(original)
            properties = files["suite-build.properties"].decode().splitlines()
            files["suite-build.properties"] = "".join(
                f"{field}={value}\n" if line.startswith(field + "=")
                else line + "\n" for line in properties).encode()
            variants.append((field, files))
        files = dict(original)
        files["lib/MegaMek.jar"] = self.rebuilt_bytes
        variants.append(("jar-bytes", files))
        files = dict(original)
        del files["data/fonts/normal.ttf"]
        variants.append(("pinned-data", files))
        for name, files in variants:
            with self.subTest(name=name):
                self.fixture.write("MegaMek", files)
                self.extracted.parent.mkdir(parents=True, exist_ok=True)
                self.extracted.write_bytes(self.rebuilt_bytes)
                output = self.run_gradle(succeeds=False)
                self.assertIn("Suite archive verification failed", output)
                self.assertFalse(self.tar.exists())
                self.assertEqual(self.extracted.read_bytes(), self.rebuilt_bytes)
        self.fixture.archives["MegaMek"].unlink()
        output = self.run_gradle(succeeds=False)
        self.assertIn("Suite archive verification failed", output)
        self.assertFalse(self.tar.exists())

    def test_external_verification_never_prepares_or_replaces_companion(self):
        archive_flag = f"-PsuiteArchiveFile={self.fixture.archives['MegaMekLab']}"
        for output in (
            self.run_gradle("verifyExternal", extra=(archive_flag,)),
            self.run_gradle(":megameklab:verifySuiteArchive", project=REPOSITORY,
                            extra=(archive_flag, "--dry-run")),
        ):
            self.assertNotIn(":prepareSuiteMegaMekCompanion", output)
            self.assertNotIn(":stageFiles", output)
            self.assertNotIn(":distTar", output)
            self.assertNotIn(":jar", output)
        self.assertFalse(self.extracted.exists())

    def test_companion_input_requires_suite_version(self):
        output = self.run_gradle(suite=False, succeeds=False)
        self.assertIn("suiteMegaMekArchiveFile requires suiteReleaseVersion", output)
        self.assertFalse(self.tar.exists())


if __name__ == "__main__":
    unittest.main()
