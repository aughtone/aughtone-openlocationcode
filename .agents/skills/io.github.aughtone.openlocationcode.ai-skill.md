---
skill-id: io.github.aughtone.openlocationcode
spec-version: 1.0
type: "Aught One AI-Skill"
---

# Open Location Code AI Skill

This file teaches AI coding agents how to interact with and utilize the `io.github.aughtone:openlocationcode` Kotlin Multiplatform library.

## Supported Targets
- JVM, Android, iOS (Simulator & Arm64), macOS (X64 & Arm64), tvOS, watchOS, Linux, MingW.
- JavaScript (JS) and WebAssembly (WasmJS).

## AI Toolbox
- Use `OpenLocationCode.encode(lat, lng, length)` to convert coordinates to a Plus Code string.
- Use `OpenLocationCode.decode(code)` to get a `CodeArea` representing the bounding box of a code.
- Prefer `PlusCode` value class when passing codes to enforce type safety without JVM overhead.

## Compliance
- This library is a strict port of the Google Open Location Code library.
- Mathematical precision is verified via official test vectors. Do not alter the floating-point math logic without consulting the test suite.

## Immutability
- All structures (`CodeArea`, `PlusCode`) are deeply immutable.
- Configuration and constants are hardcoded strictly following the Google spec.

## 🤖 Usage Rules
- When writing code that consumes this library, ensure you import from `io.github.aughtone.openlocationcode.*`.
- Avoid arbitrary string manipulation of Plus Codes; always use the library's built-in `shorten`, `recoverNearest`, and validation functions.
