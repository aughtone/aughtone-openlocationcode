# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.0.1-alpha1] - 2026-05-30

### Added
- Pure Kotlin Multiplatform (KMP) implementation of Google's Open Location Code (Plus Codes).
- Support for JVM, Android, iOS, macOS, tvOS, watchOS, Linux, MingW, JS, and WasmJS targets.
- Value class `PlusCode` for strong compile-time type-safety without JVM runtime overhead.
- Direct mathematical port of Open Location Code logic, passing official Google test vectors.
- Automated version & checksum synchronization in `Package.swift` for Swift Package Manager (SPM).
- Build and Maven Central publication integration via `vanniktech-mavenPublish` plugin.
- AI-Skill definition files for local and embedded agent discoverability.
