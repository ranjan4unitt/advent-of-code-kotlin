import kotlin.math.pow

fun main() {
    val input = readInput("001_test")
    part1(input).println()
    part2(input).println()
}
data class Card(val id: Int, val winningNumbers : List<Int>, val hand: List<Int>, var copies: Int=1) {
    fun winners(): Set<Int> {
        return winningNumbers.toSet().intersect(hand.toSet())
    }
    fun points(): Int {
        return 2.0.pow((winners().size - 1).toDouble()).toInt()
    }

    fun incrementCopies(incr: Int) {
        copies += incr
    }
}
fun parse(line: String): Card {
    try {
        val id = line.split(":")[0].split(" ").filter { it.isNotEmpty() }[1].trim().toInt()
        val winners = line.split(":")[1].trim().split("|")[0].trim().split(" ").filter { it.isNotEmpty() }
            .map { it.trim().toInt() }
        val hand = line.split(":")[1].trim().split("|")[1].trim().split(" ").filter { it.isNotEmpty() }
            .map { it.trim().toInt() }
        println(winners)
        println(hand)
        return Card(id, winners, hand)
    } catch (e: Exception) {
        println("Error parsing line: $line")
        throw e
    }
}
fun part1(input: List<String>): Int {
    return input.map { parse(it) }.sumOf { it.points() }
}

fun part2(input: List<String>): Int {
    val idToCard = input.map { parse(it) }.associateBy { it.id }
    idToCard.forEach{(id, card) -> if(card.winners().isNotEmpty())
        for (i in 1..card.winners().size) {
            idToCard[id + i]?.incrementCopies(card.copies)
        }
    }
    return idToCard.values.sumOf { card -> card.copies }
}
