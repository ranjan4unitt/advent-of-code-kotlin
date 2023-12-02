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

    fun findDigits(before: String): String {
        if (before.isEmpty()) return ""
        val digitMap = mapOf(
            "one" to 1, "two" to 2, "three" to 3, "four" to 4,
            "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9
        )
        var fdig = ""
        var rest = ""
        var lowIdx = Int.MAX_VALUE
        for (key in digitMap.keys) {
            if (before.contains(key)) {
                val idx = before.indexOf(key)
                if (idx < lowIdx) {
                    lowIdx = idx
                    fdig = digitMap[key].toString()
                    rest = before.drop(idx + key.length)
                }
            }
        }
        if (fdig.isNotEmpty()) return fdig + findDigits(rest)
        return fdig
    }

    fun toNumberStr(s: String): String {
        val before = s.takeWhile { c -> !c.isDigit() }
        val dig = s.indexOfFirst { c -> c.isDigit() }
        if (dig != -1) return "${findDigits(before)}${s[dig]}${toNumberStr(s.drop(dig + 1))}"
        return findDigits(before)
    }


     val numericDigits: Map<String, Int> = (1..9).associateBy(Int::toString)
     val speltDigits: Map<String, Int> =
        listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine").zip(1..9).toMap()
    fun parseCalibrationValue(line: String, allowableDigits: Map<String, Int> = numericDigits): Int {
        val first = allowableDigits.keys.filter(line::contains).minBy(line::indexOf)
        val last = allowableDigits.keys.maxBy(line::lastIndexOf)
        return "${allowableDigits.getValue(first)}${allowableDigits.getValue(last)}".toInt()
    }
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("001_test")
    // part1(testInput).println()
    fun part2(input: List<String>): Int {
        return input.sumOf { line -> parseCalibrationValue(line, numericDigits + speltDigits) }
        /*return input.map { s ->
            val toNumberStr = toNumberStr(s.trim())
            println("$s -> $toNumberStr")
            toNumberStr.trim() }.map { s -> "${s.first()}${s.last()}".toInt() }.sum()*/
    }
     part2(testInput).println()
    //part2(listOf("two1nine","eightwothree","abcone2threexyz", "xtwone3four","4nineeightseven2","zoneight234","7pqrstsixteen")).println()
    /* val input = readInput("Day01")
     part1(input).println()
     part2(input).println()*/
}
