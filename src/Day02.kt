// Day 2 solutions

// Patterned after Todd Ginsberg's solution (tginsberg)
// Aspiring to think and code in Kotlin like Todd :)
data class Game(val id: Int, val red: Int, val green: Int, val blue: Int) {

    fun isPossibleWith(red: Int, green: Int, blue: Int) =
        this.red <= red && this.green <= green && this.blue <= blue

    fun power() = red * green * blue

    companion object {
        fun of(input: String): Game {
            val (label, sets) = input.split(": ")
            val atLeast = mutableMapOf<String, Int>()
            sets.split("; ").forEach { draw ->
                draw.split(", ").forEach { cubes ->
                    val (n, color) = cubes.split(" ")
                    atLeast[color] = n.toInt().let { maxOf(it, atLeast[color] ?: it) }
                }
            }

            val (_, id) = label.split(" ")
            return Game(
                id.toInt(),
                red = atLeast["red"] ?: 0,
                green = atLeast["green"] ?: 0,
                blue = atLeast["blue"] ?: 0
            )
        }
    }
}

fun main() {

    // PART 1
    fun part1(games: List<Game>) = games.filter {
        it.isPossibleWith(red = 12, green = 13, blue = 14)
    }.sumOf { it.id }

    // PART 2
    fun part2(games: List<Game>) = games.sumOf { it.power() }

    // Test Parts 1 & 2
    val testInput = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """.trimIndent().lines().map { Game.of(it) }

    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    // Day 2 solutions
    val games = readInput("Day02").map { Game.of(it) }
    check(part1(games) == 2600)
    check(part2(games) == 86036)
}