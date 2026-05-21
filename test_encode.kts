import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

    const val CODE_PRECISION_NORMAL = 10
    const val CODE_ALPHABET = "23456789CFGHJMPQRVWX"
    const val SEPARATOR = '+'
    const val PADDING_CHARACTER = '0'
    const val SEPARATOR_POSITION = 8
    const val MIN_DIGIT_COUNT = 2
    const val MAX_DIGIT_COUNT = 15
    const val PAIR_CODE_LENGTH = 10
    const val GRID_CODE_LENGTH = MAX_DIGIT_COUNT - PAIR_CODE_LENGTH
    val ENCODING_BASE = CODE_ALPHABET.length
    const val LATITUDE_MAX = 90L
    const val LONGITUDE_MAX = 180L
    const val GRID_COLUMNS = 4
    const val GRID_ROWS = 5
    const val LAT_INTEGER_MULTIPLIER = 8000L * 3125L
    const val LNG_INTEGER_MULTIPLIER = 8000L * 1024L
    val LAT_MSP_VALUE = LAT_INTEGER_MULTIPLIER * ENCODING_BASE * ENCODING_BASE
    val LNG_MSP_VALUE = LNG_INTEGER_MULTIPLIER * ENCODING_BASE * ENCODING_BASE

    fun encode(latitude: Double, longitude: Double, codeLength: Int): String {
        val integers = degreesToIntegers(latitude, longitude)
        return encodeIntegers(integers[0], integers[1], codeLength)
    }

    fun encodeIntegers(latOriginal: Long, lngOriginal: Long, codeLengthOriginal: Int): String {
        var lat = latOriginal
        var lng = lngOriginal
        var codeLength = codeLengthOriginal
        codeLength = min(codeLength, MAX_DIGIT_COUNT)

        val revCodeBuilder = StringBuilder()
        if (codeLength > PAIR_CODE_LENGTH) {
            for (i in 0 until GRID_CODE_LENGTH) {
                val latDigit = lat % GRID_ROWS
                val lngDigit = lng % GRID_COLUMNS
                val ndx = (latDigit * GRID_COLUMNS + lngDigit).toInt()
                revCodeBuilder.append(CODE_ALPHABET[ndx])
                lat /= GRID_ROWS
                lng /= GRID_COLUMNS
            }
        } else {
            lat = (lat / GRID_ROWS.toDouble().pow(GRID_CODE_LENGTH)).toLong()
            lng = (lng / GRID_COLUMNS.toDouble().pow(GRID_CODE_LENGTH)).toLong()
        }
        for (i in 0 until PAIR_CODE_LENGTH / 2) {
            revCodeBuilder.append(CODE_ALPHABET[(lng % ENCODING_BASE).toInt()])
            revCodeBuilder.append(CODE_ALPHABET[(lat % ENCODING_BASE).toInt()])
            lat /= ENCODING_BASE
            lng /= ENCODING_BASE
            if (i == 0) {
                revCodeBuilder.append(SEPARATOR)
            }
        }
        val codeBuilder = revCodeBuilder.reverse()

        if (codeLength < SEPARATOR_POSITION) {
            for (i in codeLength until SEPARATOR_POSITION) {
                codeBuilder[i] = PADDING_CHARACTER
            }
        }
        return codeBuilder.substring(0, max(SEPARATOR_POSITION + 1, codeLength + 1))
    }

    fun degreesToIntegers(latitude: Double, longitude: Double): LongArray {
        var lat = floor(latitude * LAT_INTEGER_MULTIPLIER).toLong()
        var lng = floor(longitude * LNG_INTEGER_MULTIPLIER).toLong()

        lat += LATITUDE_MAX * LAT_INTEGER_MULTIPLIER
        if (lat < 0) {
            lat = 0
        } else if (lat >= 2 * LATITUDE_MAX * LAT_INTEGER_MULTIPLIER) {
            lat = 2 * LATITUDE_MAX * LAT_INTEGER_MULTIPLIER - 1
        }
        
        lng += LONGITUDE_MAX * LNG_INTEGER_MULTIPLIER
        if (lng < 0) {
            lng = lng % (2 * LONGITUDE_MAX * LNG_INTEGER_MULTIPLIER) + 2 * LONGITUDE_MAX * LNG_INTEGER_MULTIPLIER
        } else if (lng >= 2 * LONGITUDE_MAX * LNG_INTEGER_MULTIPLIER) {
            lng %= 2 * LONGITUDE_MAX * LNG_INTEGER_MULTIPLIER
        }
        return longArrayOf(lat, lng)
    }

println("degreesToIntegers(40.6, 129.7) = " + degreesToIntegers(40.6, 129.7).toList())
println("encode(40.6, 129.7, 8) = " + encode(40.6, 129.7, 8))
