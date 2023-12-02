
// Day 1 solutions
fun main() {

    // PART 1

    fun part1(input: List<String>) : Int {
        return input.size
    }

    // PART 2

    fun part2(input: List<String>) : Int {
        return input.size
    }

    val testInput1 = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green        
    """.trimIndent().lines()

    check(part1(testInput1) == 8)

    // Day 2 solutions
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
