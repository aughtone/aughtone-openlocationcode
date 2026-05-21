#!/bin/bash
cd src/commonTest/resources
echo "package com.aughtone.openlocationcode" > ../kotlin/com/aughtone/openlocationcode/TestData.kt
echo "" >> ../kotlin/com/aughtone/openlocationcode/TestData.kt
echo "object TestData {" >> ../kotlin/com/aughtone/openlocationcode/TestData.kt

for file in validityTests.csv encoding.csv decoding.csv shortCodeTests.csv; do
  varname=$(echo "$file" | sed 's/\.csv//')
  echo "    val ${varname} = listOf(" >> ../kotlin/com/aughtone/openlocationcode/TestData.kt
  cat "$file" | sed 's/"/\\"/g' | while read -r line; do
    echo "        \"$line\"," >> ../kotlin/com/aughtone/openlocationcode/TestData.kt
  done
  echo "    )" >> ../kotlin/com/aughtone/openlocationcode/TestData.kt
done
echo "}" >> ../kotlin/com/aughtone/openlocationcode/TestData.kt
