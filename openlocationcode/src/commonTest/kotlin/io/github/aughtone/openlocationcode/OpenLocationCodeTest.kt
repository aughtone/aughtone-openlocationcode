/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.aughtone.openlocationcode

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.math.abs

class OpenLocationCodeTest {

    private fun assertClose(expected: Double, actual: Double, delta: Double = 1e-10) {
        assertTrue(abs(expected - actual) <= delta, "Expected $expected but got $actual (delta $delta)")
    }

    @Test
    fun testValidity() {
        val lines = TestData.validityTests
        for (rawLine in lines) {
            val line = rawLine.trim()
            if (line.isBlank() || line.startsWith("#")) continue
            val parts = line.split(",")
            if (parts.size < 5) continue
            val code = parts[0]
            val isValid = parts[1] == "true"
            val isShort = parts[2] == "true"
            val isFull = parts[3] == "true"
            
            assertEquals(isValid, OpenLocationCode.isValidCode(code), "isValidCode failed for $code")
            assertEquals(isShort, OpenLocationCode.isShortCode(code), "isShortCode failed for $code")
            assertEquals(isFull, OpenLocationCode.isFullCode(code), "isFullCode failed for $code")
        }
    }

    @Test
    fun testEncoding() {
        val lines = TestData.encoding
        var failedEncodings = 0
        var totalEncodings = 0
        for (rawLine in lines) {
            val line = rawLine.trim()
            if (line.isBlank() || line.startsWith("#")) continue
            val parts = line.split(",")
            if (parts.size < 4) continue
            val lat = parts[0].toDouble()
            val lng = parts[1].toDouble()
            val latInt = parts[2].toLong()
            val lngInt = parts[3].toLong()
            val length = parts[4].toInt()
            val expectedCode = if (parts.size > 5) parts[5] else ""
            
            // 1. Test encodeIntegers (should pass 100%)
            val encodedInt = OpenLocationCode.encodeIntegers(latInt, lngInt, length)
            assertEquals(expectedCode, encodedInt, "encodeIntegers failed for $latInt, $lngInt, length $length")
            
            // 2. Test encode (from degrees)
            // Note: Floating-point arithmetic on Doubles varies slightly between CPUs and runtime
            // environments. Google's Java reference implementation deliberately allows a 5% failure 
            // rate when encoding purely from degree values due to slight variance triggering off-by-one 
            // bounds. We replicate that 5% tolerance here to exactly match the reference tests.
            val encoded = OpenLocationCode.encode(lat, lng, length)
            if (encoded != expectedCode) {
                failedEncodings++
            }
            totalEncodings++
        }
        
        val errorRate = failedEncodings.toDouble() / totalEncodings
        assertTrue(errorRate <= 0.05, "Too many encoding errors: $errorRate")
    }

    @Test
    fun testDecoding() {
        val lines = TestData.decoding
        for (rawLine in lines) {
            val line = rawLine.trim()
            if (line.isBlank() || line.startsWith("#")) continue
            val parts = line.split(",")
            if (parts.size < 6) continue
            val code = parts[0]
            val length = parts[1].toInt()
            val southLat = parts[2].toDouble()
            val westLng = parts[3].toDouble()
            val northLat = parts[4].toDouble()
            val eastLng = parts[5].toDouble()
            
            val area = OpenLocationCode.decode(code)
            assertEquals(length, area.length, "decode length failed for $code")
            assertClose(southLat, area.southLatitude)
            assertClose(westLng, area.westLongitude)
            assertClose(northLat, area.northLatitude)
            assertClose(eastLng, area.eastLongitude)
        }
    }

    @Test
    fun testShortCodes() {
        val lines = TestData.shortCodeTests
        for (rawLine in lines) {
            val line = rawLine.trim()
            if (line.isBlank() || line.startsWith("#")) continue
            val parts = line.split(",")
            if (parts.size < 5) continue
            val fullCode = parts[0]
            val refLat = parts[1].toDouble()
            val refLng = parts[2].toDouble()
            val shortCode = parts[3]
            val testType = parts[4] // B = Both, S = Shorten, R = Recover
            
            if (testType == "B" || testType == "S") {
                val shortened = OpenLocationCode.shorten(fullCode, refLat, refLng)
                assertEquals(shortCode, shortened, "shorten failed for $fullCode")
            }
            if (testType == "B" || testType == "R") {
                val recovered = OpenLocationCode.recover(shortCode, refLat, refLng)
                assertEquals(fullCode, recovered, "recover failed for $shortCode")
            }
        }
    }
}
