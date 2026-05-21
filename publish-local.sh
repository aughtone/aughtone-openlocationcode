#!/bin/sh
./gradlew -Pskip-signing=true check \
  -x :openlocationcode:watchosSimulatorArm64Test \
  -x :openlocationcode:tvosSimulatorArm64Test \
  -x :openlocationcode:compileTestDevelopmentExecutableKotlinWasmJs \
  publishToMavenLocal
exit 0
