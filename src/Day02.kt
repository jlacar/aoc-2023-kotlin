// Day 2 solutions
fun main() {

    // PART 1

    val configuration = mapOf("red" to 12, "green" to 13, "blue" to 14)

    fun tooMany(cubes: String) = cubes.split(", ")
        .sumOf { it.split(" ").let { (n, color) ->
            val drawn = n.toInt()
            if (configuration[color]!! < drawn) drawn else 0
        }} > 0

    fun enoughCubes(draws: List<String>) = (draws.firstOrNull { cubes -> tooMany(cubes) } ?: "").isBlank()

    fun possible(game: String) : Int =
        game.split(": ").let { (id, draws) ->
            if (enoughCubes(draws.split("; "))) { id.split(" ")[1].toInt() } else 0
        }

    fun part1(input: List<String>) = input.sumOf { game -> possible(game) }

    // PART 2

    fun power(drawSets: String) : Int {
        val hist = mutableMapOf("red" to 0, "blue" to 0, "green" to 0)
        drawSets.split("; ").map { draw ->
            draw.split(", ").map { cubes ->
                cubes.split(" ").let { (n, color) -> color to n.toInt() }
            }
        }.flatten().forEach { (color, count) ->
            if (hist[color]!! < count) hist[color] = count
        }
        return hist.toList().fold(1) { acc, (_, count) -> acc * count }
    }

    fun part2(input: List<String>) : Int {
        return input.sumOf { game -> power(game.split(": ")[1]) }
    }

    // Test Parts 1 & 2
    val testInput = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """.trimIndent().lines()

    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    // Day 2 solutions
    val input = readInput("Day02")
    check(part1(input) == 2600)
    check(part2(input) == 86036)
}
