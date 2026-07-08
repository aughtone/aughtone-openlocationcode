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
            url: "https://github.com/aughtone/aughtone-openlocationcode/releases/download/v0.0.1-alpha3/OpenLocationCode.xcframework.zip",
            checksum: "e8e939ae736599d5c221471d90afd9804c134607e7bc5a40e4cd25f37b197850"
        )
    ]
)
