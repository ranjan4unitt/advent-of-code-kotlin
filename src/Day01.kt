fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        for(cv in input){
            val digits = StringBuilder()
            for ( c in cv.toCharArray())
                if (c.isDigit()) digits.append(c)
            val allDigits = digits.toString()
            total += "${allDigits.first()}${allDigits.last()}".toInt()
        }
        return total
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("001_test")
    part1(testInput).println()
   /* val input = readInput("Day01")
    part1(input).println()
    part2(input).println()*/
}
