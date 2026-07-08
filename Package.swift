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
            checksum: "559ee64115fe76223e9b7a4a96a8755ebcf7ec32224ae6ac833d1334db3d5026"
        )
    ]
)
