# Aught One - Open Location Code (Plus Codes)

This is a pure Kotlin Multiplatform (KMP) port of Google's [Open Location Code](https://github.com/google/open-location-code), also known as Plus Codes.

Open Location Code is a technology that gives a way of encoding location into a format that is easier to use than latitude and longitude.

> **Attribution:** This library is a direct Kotlin port of the original Java implementation maintained by Google. The core logic, constants, and math were derived from the [official Google open-location-code repository](https://github.com/google/open-location-code).

## Features
- **100% Pure Kotlin**: Built entirely in the `commonMain` source set. No `expect`/`actual` platform wrappers required.
- **Idiomatic APIs**: Utilizes Kotlin `value class` to represent the `PlusCode` string, providing strong type-safety without memory overhead.
- **Fully Compliant**: Passes all official Google test vectors for validity, encoding, decoding, and short codes with a 5% float tolerance for platform-specific math variance.
- **Multiplatform Support**: Supports JVM, Android, iOS, macOS, tvOS, watchOS, Linux, MingW, JS, and WasmJS.

---

## Installation

### Kotlin Multiplatform / JVM / Android (Maven Central)

You can add this library to your KMP, Android, or JVM project by declaring the dependency in your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("io.github.aughtone:openlocationcode:0.0.1-alpha1")
}
```

### iOS / Swift Package Manager (Xcode)

For native Swift development, this library is distributed as a precompiled XCFramework.

1. Open your Xcode project.
2. Go to **File > Add Package Dependencies**.
3. Enter the repository URL: `https://github.com/aughtone/aughtone-openlocationcode`
4. Select the **Up to Next Major Version** rule. *(Note: If you are using a prerelease tag like `-alpha1`, you must select **Exact Version**).*
5. Add the `OpenLocationCode` product to your target.

---

## Usage

The API is intentionally similar to the original Google library, modernized for Kotlin.

### Encoding a Location

To encode a latitude and longitude into a Plus Code:

```kotlin
import io.github.aughtone.openlocationcode.OpenLocationCode

// Encode with default precision (10 digits)
val code = OpenLocationCode.encode(47.365590, 8.524997)
println(code.value) // Output: 8FVC9G8F+6X

// Encode with specified code length
val preciseCode = OpenLocationCode.encode(47.365590, 8.524997, 11)
println(preciseCode.value) // Output: 8FVC9G8F+6XW
```

### Decoding a Plus Code

To decode a Plus Code back into a bounding box (`CodeArea`):

```kotlin
import io.github.aughtone.openlocationcode.OpenLocationCode
import io.github.aughtone.openlocationcode.PlusCode

val plusCode = PlusCode("8FVC9G8F+6X")
val area = OpenLocationCode.decode(plusCode)

println("Center Latitude: ${area.centerLatitude}")
println("Center Longitude: ${area.centerLongitude}")
println("Southwest Corner: ${area.southLatitude}, ${area.westLongitude}")
```

### Shortening a Plus Code

If you know the user's current location, you can shorten a Plus Code by dropping the region characters:

```kotlin
val fullCode = PlusCode("8FVC9G8F+6X")
val referenceLat = 47.365590
val referenceLng = 8.524997

val shortCode = OpenLocationCode.shorten(fullCode, referenceLat, referenceLng)
println(shortCode.value) // Output: 9G8F+6X
```

### Recovering a Short Code

To expand a short code back to a full code using a reference location:

```kotlin
val shortCode = PlusCode("9G8F+6X")
val referenceLat = 47.365590
val referenceLng = 8.524997

val recoveredCode = OpenLocationCode.recoverNearest(shortCode, referenceLat, referenceLng)
println(recoveredCode.value) // Output: 8FVC9G8F+6X
```

### Validation

You can validate strings to see if they are structurally sound Plus Codes:

```kotlin
val isValid = OpenLocationCode.isValidCode("8FVC9G8F+6X") // true
val isFull = OpenLocationCode.isFullCode("8FVC9G8F+6X") // true
val isShort = OpenLocationCode.isShortCode("9G8F+6X") // true
```

---

## 🤖 AI-Assisted Development

This repository is optimized for AI-Assisted Development. If you are an AI agent, you must read the [Agent Onboarding Guide](AGENTS.md) before contributing to this codebase.

---

## License

This project is licensed under the Apache License, Version 2.0. See the `LICENSE` file for details.
