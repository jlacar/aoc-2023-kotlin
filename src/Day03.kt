fun main() {

    // PART 1

    fun part1(schematic: List<String>): Int {
        return schematic.size
    }

    // PART 2

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Util

    fun bordered(raw: List<String>): List<String> {
        val border = ".".repeat(raw[0].length)
        return listOf(border) + raw + border
    }

    // Tests

    val testInput = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...${'$'}.*....
        .664.598..
    """.trimIndent().lines()

    testInput.println()
    check(part1(bordered(testInput)) == 4361)

    // Day 3 Solution
//    val input = bordered(readInput("Day03"))
//    println(part1(input))
//    println(part2(input))
}
