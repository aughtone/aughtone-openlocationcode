// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "OpenLocationCode",
    platforms: [
        .iOS(.v13),
        .macOS(.v10_15),
        .tvOS(.v13),
        .watchOS(.v6)
    ],
    products: [
        .library(
            name: "OpenLocationCode",
            targets: ["OpenLocationCode"])
    ],
    targets: [
        .binaryTarget(
            name: "OpenLocationCode",
            url: "https://github.com/aughtone/aughtone-openlocationcode/releases/download/v0.0.1-alpha1/OpenLocationCode.xcframework.zip",
            checksum: "PASTE_CHECKSUM_HERE"
        )
    ]
)
