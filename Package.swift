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
            checksum: "66c7132ecb3daaf59945901824653a939f6aa9f5276378736c7bf5ec0b746198"
        )
    ]
)
