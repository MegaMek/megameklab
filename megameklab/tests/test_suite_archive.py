"""Synthetic external suite verifier regression; run explicitly, not during packaging."""

import io
import os
from pathlib import Path
import subprocess
import tarfile
import tempfile
import unittest
import zipfile


ROOT = Path(__file__).resolve().parents[2]
VERSION = "0.51.01"
GAME_VERSION = "0.51.00"


def git(repo, *args):
    return subprocess.check_output(["git", "-C", str(repo), *args])


def jar(entries):
    stream = io.BytesIO()
    with zipfile.ZipFile(stream, "w") as archive:
        for name, value in entries.items():
            archive.writestr(name, value)
    return stream.getvalue()


def tarball(path, root, files):
    with tarfile.open(path, "w:gz") as archive:
        for name, content in files.items():
            entry = tarfile.TarInfo(root + "/" + name)
            entry.size = len(content)
            archive.addfile(entry, io.BytesIO(content))


class SuiteArchiveTest(unittest.TestCase):
    def test_external_archive_and_tampering(self):
        pins = {
            name: git(ROOT / repo, "rev-parse", "HEAD").decode().strip()
            for name, repo in [
                ("suiteMegaMekCommit", "../megamek"),
                ("suiteMegaMekLabCommit", "."),
                ("suiteMekHQCommit", "../mekhq"),
                ("suiteMmDataCommit", "../mm-data"),
            ]
        }
        identity = {
            "schemaVersion": "1", "product": "MegaMekLab", "version": VERSION,
            "megamekVersion": GAME_VERSION, "minimumJavaVersion": "21",
            **{field: pins[prop] for field, prop in [
                ("megamekCommit", "suiteMegaMekCommit"),
                ("megameklabCommit", "suiteMegaMekLabCommit"),
                ("mekhqCommit", "suiteMekHQCommit"),
                ("mmDataCommit", "suiteMmDataCommit"),
            ]},
        }
        game_identity = {key: value for key, value in identity.items()
                         if key != "megamekVersion"}
        game_identity["product"] = "MegaMek"
        game_identity["version"] = GAME_VERSION
        game_identity["megameklabCommit"] = "a" * 40
        game_identity["mekhqCommit"] = "b" * 40
        def record(properties):
            return "".join(f"{key}={value}\n" for key, value in properties.items()).encode()

        game_jar = jar({"Version.properties":
                        b"major=0\nminor=51\npatch=00\nrevision=\n"})
        lab_jar = jar({"META-INF/MANIFEST.MF":
                       b"Manifest-Version: 1.0\r\nImplementation-Version: 0.51.01\r\n\r\n"})
        icon = git(ROOT / "../mm-data", "show",
                   pins["suiteMmDataCommit"] + ":data/images/misc/megameklab.ico")
        derived = {"data/mekfiles/unit_files.zip": b"synthetic units",
                   "data/rat/rat_default.zip": b"synthetic rat"}
        with tempfile.TemporaryDirectory() as temporary:
            folder = Path(temporary)
            companion = folder / f"MegaMek-{GAME_VERSION}.tar.gz"
            target = folder / f"MegaMekLab-{VERSION}.tar.gz"
            tarball(companion, f"MegaMek-{GAME_VERSION}", {
                "suite-build.properties": record(game_identity),
                "MegaMek.jar": game_jar, "lib/MegaMek.jar": game_jar,
                **derived,
            })
            files = {
                "suite-build.properties": record(identity),
                "MegaMekLab.jar": lab_jar, "lib/MegaMekLab.jar": lab_jar,
                "lib/MegaMek.jar": game_jar,
                "MegaMekLab.sh": b"synthetic", "bin/MegaMekLab": b"synthetic",
                "bin/MegaMekLab.bat": b"synthetic",
                "data/images/misc/megameklab.ico": icon,
                **derived,
            }

            def verify(expected, message=None):
                result = subprocess.run(
                    [str(ROOT / ("gradlew.bat" if os.name == "nt" else "gradlew")),
                     ":megameklab:verifySuiteArchive", "--console=plain",
                     f"-PsuiteReleaseVersion={VERSION}",
                     f"-PsuiteMegaMekVersion={GAME_VERSION}",
                     f"-PsuiteArchiveFile={target}",
                     f"-PsuiteMegaMekArchiveFile={companion}",
                     *[f"-P{key}={value}" for key, value in pins.items()]],
                    cwd=ROOT, capture_output=True, text=True, check=False,
                )
                self.assertEqual(result.returncode == 0, expected,
                                 result.stdout + result.stderr)
                self.assertNotIn("> Task :megameklab:distTar", result.stdout)
                self.assertNotIn("> Task :megameklab:jar", result.stdout)
                if message:
                    self.assertIn(message, result.stdout + result.stderr)

            tarball(target, f"MegaMekLab-{VERSION}", files)
            verify(True)
            files["suite-build.properties"] = b" " * 65537
            tarball(target, f"MegaMekLab-{VERSION}", files)
            verify(False, "Oversized suite identity")
            files["suite-build.properties"] = record(identity)
            # A large unrelated payload must not be materialized by the verifier.
            files["docs/large-fixture"] = b"x" * (40 * 1024 * 1024)
            tarball(target, f"MegaMekLab-{VERSION}", files)
            verify(True)
            del files["docs/large-fixture"]
            files["data/large-fixture"] = b"x" * (40 * 1024 * 1024)
            tarball(target, f"MegaMekLab-{VERSION}", files)
            verify(False, "Unpinned or mismatched archived mm-data")
            del files["data/large-fixture"]
            tarball(companion, f"MegaMek-{GAME_VERSION}", {
                "suite-build.properties": record(game_identity),
                "MegaMek.jar": game_jar, **derived,
            })
            verify(False, "MegaMek companion identity or jar")
            tarball(companion, f"MegaMek-{GAME_VERSION}", {
                "suite-build.properties": record(game_identity),
                "MegaMek.jar": game_jar, "lib/MegaMek.jar": game_jar, **derived,
            })
            game_identity["minimumJavaVersion"] = "17"
            tarball(companion, f"MegaMek-{GAME_VERSION}", {
                "suite-build.properties": record(game_identity),
                "MegaMek.jar": game_jar, "lib/MegaMek.jar": game_jar, **derived,
            })
            verify(False, "MegaMek companion identity or jar")
            game_identity["minimumJavaVersion"] = "21"
            tarball(companion, f"MegaMek-{GAME_VERSION}", {
                "suite-build.properties": record(game_identity),
                "MegaMek.jar": game_jar, "lib/MegaMek.jar": game_jar, **derived,
            })
            files["data/images/misc/megameklab.ico"] = b"tampered icon"
            tarball(target, f"MegaMekLab-{VERSION}", files)
            verify(False, "Archived mm-data differs from pin")
            files["data/images/misc/megameklab.ico"] = icon
            files["lib/MegaMek.jar"] = jar({"Version.properties": b"major=0\nminor=50\npatch=01\n"})
            tarball(target, f"MegaMekLab-{VERSION}", files)
            verify(False, "Lab MegaMek jar differs from companion archive")
            files["lib/MegaMek.jar"] = game_jar
            files["../escape"] = b"unsafe"
            tarball(target, f"MegaMekLab-{VERSION}", files)
            verify(False, "Unsafe or duplicate suite archive entry")


if __name__ == "__main__":
    unittest.main()
