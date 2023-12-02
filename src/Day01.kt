fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        for (cv in input) {
            val digits = StringBuilder()
            for (c in cv.toCharArray())
                if (c.isDigit()) digits.append(c)
            val allDigits = digits.toString()
            total += "${allDigits.first()}${allDigits.last()}".toInt()
        }
        return total
    }

    fun toCalDigit(s: String): Int {
        val digitMap = mapOf(
            "one" to 1, "two" to 2, "three" to 3, "four" to 4,
            "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9,
            "1" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5, "6" to 6, "7" to 7, "8" to 8, "9" to 9
        )
        var firstIdx = Integer.MAX_VALUE
        var firstDigit = ""
        digitMap.keys.forEach { key ->
            val idx = s.indexOf(key)
            if (idx != -1 && idx < firstIdx) {
                firstIdx = idx
                firstDigit = digitMap.getValue(key).toString()
            }
        }
        var lastIdx = Integer.MIN_VALUE
        var lastDigit = ""
        digitMap.keys.forEach { key ->
            val idx = s.lastIndexOf(key)
            if (idx != -1 && idx > lastIdx) {
                lastIdx = idx
                lastDigit = digitMap.getValue(key).toString()
            }
        }
        return "$firstDigit$lastDigit".toInt()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("001_test")
    //part1(testInput).println()
    fun part2(input: List<String>): Int {
        return input.sumOf { line -> toCalDigit(line) }
    }
    part2(testInput).println()
    part2(listOf("two1nine","eightwothree","abcone2threexyz", "xtwone3four","4nineeightseven2","zoneight234","7pqrstsixteen")).println()

}
