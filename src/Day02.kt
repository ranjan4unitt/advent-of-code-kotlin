data class Game(val id: Int, val red: Int = 0, val blue: Int = 0, val green: Int = 0) {
    val cube: Int = (if (red == 0) 1 else red) * (if (blue == 0) 1 else blue) * (if (green == 0) 1 else green)

    fun isPossible(bag: Game): Boolean {
        return (bag.red >= red && bag.blue >= blue && bag.green >= green)
    }
}

fun main() {
    fun toGame(line: String): Game {
        val gameId = line.split(":")[0].split(" ")[1].toInt()
        val ballCombo = line.split(":")[1].split(";")
        var ballCounter = mutableMapOf("red" to 0, "blue" to 0, "green" to 0)
        ballCombo.forEach { balls ->
            balls.split(",").forEach { ball ->
                val color = ball.trim().split(" ")[1].trim()
                val count = ball.trim().split(" ")[0].trim().toInt()
                ballCounter[color] = (ballCounter[color] ?: 0).coerceAtLeast(count)
            }
        }
        return Game(gameId, ballCounter["red"] ?: 0, ballCounter["blue"] ?: 0, ballCounter["green"] ?: 0)
    }

    fun part1(input: List<String>): Int {
        val bag = Game(-1, 12, 14, 13)
        return input.map { line -> toGame(line) }.filter { game -> game.isPossible(bag) }.map { game -> game.id }.sum()
    }


    fun part2(input: List<String>): Int {
        return input.map { line -> toGame(line) }.sumOf { game -> game.cube }
    }

    val input = readInput("001_test")
    part1(input).println()
    part2(input).println()
}