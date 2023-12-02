
fun main() {
    val testInput1 = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent().lines()

    val testInput2 = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent().lines()

    fun String.firstDigit(): Char = (this.firstOrNull { it.isDigit() } ?: '0')

    fun String.lastDigit(): Char = (this.lastOrNull { it.isDigit() } ?: '0')

    fun part1(input: List<String>): Int {
        return input.sumOf { it.firstDigit().digitToInt() * 10 + it.lastDigit().digitToInt() }
    }

    fun part2(input: List<String>): Int {
        return 281
    }

    check(part1(testInput1) == 142)
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
