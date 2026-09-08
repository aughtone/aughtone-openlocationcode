# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.0.2] - 2026-09-07

First release outside the `alpha` line.

### Added
- `NOTICE` at the repository root, recording the derivation from Google's Open Location Code. It existed but had never been committed, so the link to it from the README was broken.
- `LICENSE` and `NOTICE` are now packaged into the published JVM and Android jars under `META-INF/`.
- Provenance comments on the test data copied from `google/open-location-code`, noting the upstream path and, where applicable, that the copy was trimmed.

### Changed
- Every derived source file now carries the port's own copyright line and an Apache 2.0 §4(b) modification notice alongside Google's original notice, which is retained verbatim.
- The copyright holder is stated consistently as The Aught One Authors across `LICENSE`, `NOTICE` and the source headers.
- `AGENTS.md` records the settled header convention, what each source file actually derives from upstream, and the trademark position on the Open Location Code and Plus Codes names.
- The Maven POM `developers` block now identifies the maintainer by GitHub handle only.
- Gradle `rootProject.name` is now `AOOpenLocationCode`, disambiguating the build from the `OpenLocationCode` framework and Swift package names, which are unchanged. As a consequence the Kotlin metadata module identity changed from `io.github.aughtone:openlocationcode_OpenLocationCode` to `io.github.aughtone:openlocationcode_AOOpenLocationCode`. The Swift `import OpenLocationCode` and the Maven coordinate are unaffected.
- Enabled parallel Gradle tooling sync (`org.gradle.tooling.parallel`) for Gradle 9.4+.
- README now recommends the **Up to Next Minor Version** rule for Swift Package Manager, since a pre-1.0 minor bump may carry breaking changes.

### Removed
- The bundled AI-Skill definition files: the repository-local one under `.agents/skills/`, and the copy that shipped inside published artifacts at `META-INF/ai-skills/`. Artifacts from this release onward do not contain them.
- These files were also removed from the repository's git history. **Existing clones are incompatible** — re-clone, or `git fetch && git reset --hard origin/<branch>`. Artifacts already published to Maven Central under `0.0.1-alpha1` through `0.0.1-alpha3` are immutable and still contain the file.

## [0.0.1-alpha3] - 2026-07-08

### Changed
- Separated the Maven `group` coordinate from the Android and framework `namespace`.
- Made the watchOS and tvOS simulator availability checks compatible with Gradle's configuration cache.

## [0.0.1-alpha2] - 2026-06-28

### Added
- Root `build.gradle.kts` configuration for Yarn and WasmYarn plugins to report lock mismatches as warnings (`YarnLockMismatchReport.WARNING`).
- Explicit Yarn lock directories for JS and Wasm targets (`kotlin-js-store` and `kotlin-js-store/wasm`) to prevent implicit task dependency and execution order conflicts.

### Changed
- Upgraded Kotlin compiler version to `2.4.0`.
- Updated release GitHub Actions workflow to split operations into an Ubuntu-based `create-release` job and a macOS-based `publish` job, targeting the `master` branch.
- Configured pull request creation in the release workflow to fail gracefully if repository Actions permissions do not allow it.

## [0.0.1-alpha1] - 2026-05-30

### Added
- Pure Kotlin Multiplatform (KMP) implementation of Google's Open Location Code (Plus Codes).
- Support for JVM, Android, iOS, macOS, tvOS, watchOS, Linux, MingW, JS, and WasmJS targets.
- Value class `PlusCode` for strong compile-time type-safety without JVM runtime overhead.
- Direct mathematical port of Open Location Code logic, passing official Google test vectors.
- Automated version & checksum synchronization in `Package.swift` for Swift Package Manager (SPM).
- Build and Maven Central publication integration via `vanniktech-mavenPublish` plugin.
- AI-Skill definition files for local and embedded agent discoverability.
