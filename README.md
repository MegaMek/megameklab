# MegaMekLab — Unit Editor for MegaMek

## Table of Contents

1. [About](#about)
2. [Status](#status)
3. [Running MegaMek](#running-megameklab)
    1. [Installing Or Updating Your Java Runtime](#installing-or-updating-your-java-runtime)
        1. [Adoptium (Windows)](#adoptium-windows)
        2. [Adoptium (Mac)](#adoptium-mac)
        3. [Linux](#linux)
4. [Compiling](#compiling)
5. [Support](#support)
6. [Contact & Further Information](#contact--further-information)
7. [Licensing](#licensing)

## About

MegaMekLab is a Java program for the creation and modification of units for MegaMek.

For complete game rules, consult the Classic BattleTech rule books published by Catalyst Game Labs. These books
include [Total Warfare](https://store.catalystgamelabs.com/collections/battletech/products/battletech-total-warfare-pdf),
[Tactical Operations: Advanced Rules](https://store.catalystgamelabs.com/collections/battletech/products/battletech-tactical-operations-advanced-rules),
[Tactical Operations: Advanced Units & Equipment](https://store.catalystgamelabs.com/collections/battletech/products/battletech-tactical-operations-advanced-units-equipement),
and [Strategic Operations](https://store.catalystgamelabs.com/collections/battletech/products/battletech-strategic-operations).

## Status

| Type           | MM Status                                                                                                                                                              | MML Status                                                                                                                                                                       | MHQ Status                                                                                                                                                        |
|----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Latest Release | [![Release](https://img.shields.io/github/release/MegaMek/megamek.svg)](https://gitHub.com/MegaMek/megamek/releases/)                                                  | [![Release](https://img.shields.io/github/release/MegaMek/megameklab.svg)](https://gitHub.com/MegaMek/megameklab/releases/)                                                      | [![Release](https://img.shields.io/github/release/MegaMek/mekhq.svg)](https://gitHub.com/MegaMek/mekhq/releases/)                                                 |
| Javadocs | [![javadoc](https://badgen.net/badge/javadoc/master/red?icon=github)](https://megamek.org/megamek) | [![javadoc](https://badgen.net/badge/javadoc/master/red?icon=github)](https://megamek.org/megameklab) | [![javadoc](https://badgen.net/badge/javadoc/master/red?icon=github)](https://megamek.org/mekhq) |
| License        | [![GPLv3 license](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0.html)                                                     | [![GPLv3 license](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0.html)                                                               | [![GPLv3 license](https://img.shields.io/badge/License-GPLv3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0.html)                                                 |
| Build (CI)     | [![MM Nightly CI](https://github.com/MegaMek/megamek/workflows/MegaMek%20Nightly%20CI/badge.svg)](https://github.com/MegaMek/megamek/actions/workflows/nightly-ci.yml) | [![MML Nightly CI](https://github.com/MegaMek/megameklab/workflows/MegaMekLab%20Nightly%20CI/badge.svg)](https://github.com/MegaMek/megameklab/actions/workflows/nightly-ci.yml) | [![MHQ Nightly CI](https://github.com/MegaMek/mekhq/workflows/MekHQ%20Nightly%20CI/badge.svg)](https://github.com/MegaMek/mekhq/actions/workflows/nightly-ci.yml) |
| Issues         | [![GitHub Issues](https://badgen.net/github/open-issues/MegaMek/megamek)](https://gitHub.com/MegaMek/megamek/issues/)                                                  | [![GitHub Issues](https://badgen.net/github/open-issues/MegaMek/megameklab)](https://gitHub.com/MegaMek/megameklab/issues/)                                                      | [![GitHub Issues](https://badgen.net/github/open-issues/MegaMek/mekhq)](https://gitHub.com/MegaMek/mekhq/issues/)                                                 |
| PRs            | [![GitHub Open Pull Requests](https://badgen.net/github/open-prs/MegaMek/megamek)](https://gitHub.com/MegaMek/megamek/pull/)                                           | [![GitHub Open Pull Requests](https://badgen.net/github/open-prs/MegaMek/megameklab)](https://gitHub.com/MegaMek/megameklab/pull/)                                               | [![GitHub Open Pull Requests](https://badgen.net/github/open-prs/MegaMek/mekhq)](https://gitHub.com/MegaMek/mekhq/pull/)                                          |
| Code Coverage  | [![MegaMek codecov.io](https://codecov.io/github/MegaMek/megamek/coverage.svg)](https://codecov.io/github/MegaMek/megamek)                                             | [![MegaMekLab codecov.io](https://codecov.io/github/MegaMek/megameklab/coverage.svg)](https://codecov.io/github/MegaMek/megameklab)                                              | [![MekHQ codecov.io](https://codecov.io/github/MegaMek/mekhq/coverage.svg)](https://codecov.io/github/MegaMek/mekhq)                                              |

Note that not everything has been implemented across the suite at this time, which will lead to gaps.

## RUNNING MEGAMEKLAB

Java programs run in their own environment, called a Virtual Machine or VM for short. These Java VMs are available on
most systems from a variety of sources.

Windows users: To start MegaMekLab, run the MegaMekLab.exe file. If this fails to start MegaMekLab, see the "INSTALLING
OR UPDATING YOUR JAVA RUNTIME" section, below.

Other graphical OSes: Many other graphical OSes, such as macOS, will allow you to double-click the .jar file to run it.
If this does not work, try running MegaMek from the command line.

Running MegaMekLab from the command line: To do this using Sun Java, or most other implementations, navigate to the
directory containing the .jar file and run: `java -jar MegaMekLab.jar`.

If none of the above options work for you, see the "INSTALLING OR UPDATING YOUR JAVA RUNTIME" section, below.

### INSTALLING OR UPDATING YOUR JAVA RUNTIME

Of the versions available, we now require Java 17 LTS as the bare minimal version. Newer versions should work but are
not currently supported.

#### Adoptium (Windows)

For Windows, follow the instructions [here](https://github.com/MegaMek/megamek/wiki/Updating-to-Adoptium) to ensure Java
is installed correctly for the most seamless experience.

#### Adoptium (Mac)

For Mac, download the installer
from [Adoptium]( https://adoptium.net/temurin/releases/?os=mac&version=17&arch=aarch64&package=jre) directly for your
version of macOS and underling platform (AARCH64 is for M-Series Mac's).

#### Linux

For Linux, your distribution should have a version of Java available via your package manager.

## CUSTOM UNITS

All units (meks, vehicles, infantry, etc.) are located in the data/mekfiles directory. They may be individual files or
zipped up into archives (".zip"), and you may also create subdirectories if you like.

We recommend creating a folder called Customs in the data/mekfiles directory. Then using this folder to store all custom
units.

As of 0.49.13, We've removed the unsupported and unofficial folders. Over the years the unsupported units dropped to
only a couple. The unofficial folder is available
from [the Extras repository](https://github.com/MegaMek/megamek-extras).

### Note of file types

MegaMekLab uses two file types for units. Files with the extension MTF are meks, and all other unit types are BLK files.
Both are editable with a quality text editor, but we recommend not hand editing files as it can break the programs.

## Compiling

1) Install [Gradle](https://gradle.org/).

2) Follow the [instructions on the wiki](https://github.com/MegaMek/megamek/wiki/Working-With-Gradle) for using Gradle.

### Local suite package

Suite archive verification and data staging use the shared tooling in the sibling
MegaMek checkout (`gradle/suite_archive_verifier.py` and
`gradle/suite_archive_adapter.gradle`). Python 3.10+ is required for suite tasks;
use `-PsuitePythonExecutable=<absolute interpreter path>` if `python` on Windows
or `python3` elsewhere is unavailable. Normal non-suite builds do not require Python.

From a checkout alongside `megamek`, `mekhq`, and `mm-data`, pass all five shared inputs to
`./gradlew :megameklab:verifySuiteArchive` (or `:megameklab:distTar`):

```
-PsuiteReleaseVersion=0.51.01
-PsuiteMegaMekCommit=<40 lowercase hex HEAD>
-PsuiteMegaMekLabCommit=<40 lowercase hex HEAD>
-PsuiteMekHQCommit=<40 lowercase hex HEAD>
-PsuiteMmDataCommit=<40 lowercase hex HEAD>
```

The version must be canonical padded `major.minor.patch`, with each component within Java's integer range.
Optionally supply `-PsuiteMegaMekVersion` and `-PsuiteMegaMekLabVersion`
alongside the suite version; each defaults to it and must have the same canonical
format. Lab's archive and manifest use the Lab version, while the embedded
MegaMek runtime uses the MegaMek version. A Lab-only update can keep the
previous MegaMek release unchanged; MekHQ must then advance to bundle the new Lab.
The product version overrides the historic revision without changing tracked version files;
`extraVersion` is rejected in suite mode to avoid a differently versioned included MegaMek build.
The included MegaMek build must receive the same Gradle `-P` inputs: the archive check reads its
packaged `Version.properties` and fails if the included build did not receive the override.
Pins must match the actual sibling and local HEADs. Suite archives refuse tracked changes
and unknown untracked or ignored package-source inputs in all four checkouts.
Ignored local data mirrors must match tracked mm-data bytes; build/cache output is excluded.
Suite staging reads canonical Git blobs into a build-local directory, including
generated ZIP contents, so Windows checkout line endings do not change packaged data.
Use clean checkouts for final packaging. The normal
`megameklab/build/distributions/MegaMekLab-<version>.tar.gz` contains the existing launchers, jars,
bundled mm-data and user-config exclusions, plus a `suite-build.properties` identity file at its root.
`verifySuiteArchive` checks that file, both runtime JAR versions, the data payload and launcher layout.
These are local build tasks; they do not publish anything.

When building Lab with an already completed MegaMek archive, also supply
`-PsuiteMegaMekArchiveFile=<path>/MegaMek-<MegaMek version>.tar.gz`.
The canonical verifier checks that archive and copies its exact primary JAR into
`build/suite/companions/MegaMek.jar`. Both the main distribution and the
SVGMassPrinter distribution use those bytes, excluding any freshly rebuilt
MegaMek JAR contributed by the application plugin's runtime classpath.
Built verification checks the selected JAR and its companion archive, not a
separate sibling rebuild. An invalid or missing companion fails the build;
there is no fallback to a rebuilt JAR. Without this input, standalone suite
builds continue to build and package the sibling MegaMek JAR.

To check a previously downloaded Lab tarball without rebuilding, pass
`-PsuiteArchiveFile=<path>/MegaMekLab-<Lab version>.tar.gz` and
`-PsuiteMegaMekArchiveFile=<path>/MegaMek-<MegaMek version>.tar.gz` to
`:megameklab:verifySuiteArchive` along with the same five suite inputs and product versions.
This mode does not run `distTar` or its producers. It requires the matching MegaMek
companion archive to verify the bundled runtime JAR. The complete packaged data-file
set and generated ZIP members are checked against the declared mm-data Git commit,
rejecting missing, extra, and modified files.
An older reused MegaMek companion may carry older Lab and MekHQ commits: its own
MegaMek and mm-data pins, version, Java requirement and root/lib JAR bytes must match.
Verification streams archive entries and temporarily spools only inspected payloads;
TAR extension and ZIP directory metadata are bounded before parsing or allocation.
Both modes use the same verifier and pinned-data checks; built mode also compares
the packaged JARs with their producer outputs.
Use trusted local checkouts containing the pinned Git objects; neither mode publishes assets.
The common regressions run from MegaMek with
`python -B -m unittest discover -s gradle -p 'test_suite_*.py' -v`.
Lab's packaging regressions run from the Lab repository with
`python -B -m unittest discover -s gradle -p 'test_suite_megamek_companion.py' -v`.
They apply the production companion CopySpec in a small Gradle application,
build real tarballs with conflicting runtime JAR bytes, and use the canonical
verifier and pinned mm-data fixture. PR CI runs these checks on both supported JDKs.

### Style Guide

When contributing to this project, please enable the EditorConfig option within your IDE to ensure some basic compliance
with our [style guide](https://github.com/MegaMek/megamek/wiki/MegaMek-Coding-Style-Guide) which includes some defaults
for line length, tabs vs. spaces, etc. When all else fails, we follow
the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).

The build enforces formatting, so it does not matter which editor (or tool) wrote the code. Before you push:

```
./gradlew spotlessApply
```

That formats every Java file you changed. `./gradlew spotlessCheck` reports the same violations without changing
anything. The check runs first on every pull request and lists every violation in one go.

## Support

For bugs, crashes, or other issues you can fill out
a [GitHub issue request](https://github.com/MegaMek/MegaMekLab/issues).

## CONTACT & FURTHER INFORMATION

For more information, and to get the latest version of MegaMek, visit the [website](https://megamek.org).

For more information about the BattleTech board game, visit its [website](https://www.battletech.com).

To submit a bug report, suggestion, or feature request, please visit
our [issue tracker](https://github.com/MegaMek/megameklab/issues)

To discuss all things MegaMek, please visit our [Discord](https://discord.gg/megamek)

## Licensing

MegaMekLab is licensed under a dual-licensing approach:

### Code License

All source code is licensed under the GNU General Public License v3.0 (GPLv3). See the [LICENSE.code](LICENSE.code) file
for details.

### Data/Assets License

Game data, artwork, and other non-code assets are licensed under the Creative Commons Attribution-NonCommercial 4.0
International License (CC-BY-NC-4.0). See the [LICENSE.assets](LICENSE.assets) file for details.

### BattleTech IP Notice

MechWarrior, BattleMech, `Mech, and AeroTech are registered trademarks of The Topps Company, Inc. All Rights Reserved.
Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of InMediaRes Productions, LLC.

The BattleTech name for electronic games is a trademark of Microsoft Corporation.

MegaMek is an unofficial, fan-created digital adaptation and is not affiliated with, endorsed by, or licensed by
Microsoft Corporation, The Topps Company, Inc., or Catalyst Game Labs.

### Full Licensing Details

For complete information about licensing, including specific directories and files, please see the [LICENSE](LICENSE)
document.
