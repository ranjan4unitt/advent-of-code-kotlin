data class Symbol(val symbol: Char, val x: Int, val y: Int)

fun main() {
    fun isSymbol(c: Char): Boolean {
        return !c.isDigit() && c != '.'
    }

    fun getPartNumberFromPosition(schemeRow: CharArray, pos: Int): Int {
        var left = pos
        var numStr = ""
        while (left >= 0 && schemeRow[left].isDigit()) {
            numStr = schemeRow[left] + numStr
            left--
        }
        var right = pos + 1
        while (right < schemeRow.size && schemeRow[right].isDigit()) {
            numStr += schemeRow[right]
            right++
        }
        return if (numStr.isNotEmpty()) numStr.toInt() else 0
    }

    fun partsPerSymbol(
        scheme: Array<CharArray>
    ): MutableMap<Symbol, Set<Int>> {
        val result = mutableMapOf<Symbol, Set<Int>>() // symbol -> set of parts
        for (i in scheme.indices) {
            for (j in scheme[i].indices) {
                if (isSymbol((scheme[i][j]))) {
                    val steps = listOf(-1, 0, 1)
                    var partsPerSymbol = mutableSetOf<Int>()
                    steps.forEach { side ->
                        steps.forEach { up ->
                            if (side != 0 || up != 0) {
                                if (scheme[i + side][j + up].isDigit()) {
                                    partsPerSymbol.add(getPartNumberFromPosition(scheme[i + side], j + up))
                                }
                            }
                        }
                    }
                    result[Symbol(scheme[i][j], i, j)] = partsPerSymbol
                }
            }
        }
        return result
    }

    fun toScheme(input: List<String>): Array<CharArray> {
        val rows = input.size
        val cols = input[0].length
        val scheme: Array<CharArray> = Array(rows) { CharArray(cols) { '.' } }
        for (idx in input.indices) {
            val line = input[idx]
            for (i in line.indices) {
                scheme[idx][i] = line[i]
            }
        }
        return scheme
    }

    fun part1(input: List<String>): Int {
        val scheme = toScheme(input)
        println(scheme.joinToString("\n") { it.joinToString("") })
        val result = partsPerSymbol(scheme)
        return result.mapValues { it.value.sum() }.values.sum()
    }

    fun part2(input: List<String>): Int {
        val scheme = toScheme(input)
        var total = 0
        val result = partsPerSymbol(scheme)
        result.filterKeys { it.symbol == '*' }.filterValues { it.size == 2 }.forEach { (_, parts) ->
            total += parts.reduce { acc, i -> acc * i }
        }
        return total
    }

    val input = readInput("001_test")
    part1(input).println()
    part2(input).println()
}