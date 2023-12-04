import java.util.function.BiPredicate

fun main() {

    fun calibrate(first: Char, last: Char) = first.digitToInt() * 10 + last.digitToInt()

    fun String.firstDigit(): Char = this.firstOrNull { it.isDigit() } ?: '0'

    fun String.lastDigit(): Char = this.lastOrNull { it.isDigit() } ?: '0'

    // PART 1

    fun part1(input: List<String>) = input.sumOf {
        calibrate(it.firstDigit(), it.lastDigit())
    }

    // PART 2

    val wordsToDigit = mapOf(
        "one" to '1',
        "two" to '2',
        "three" to '3',
        "four" to '4',
        "five" to '5',
        "six" to '6',
        "seven" to '7',
        "eight" to '8',
        "nine" to '9',
    )

    val words = wordsToDigit.keys.toList()
    val digits = "123456789".map { it.toString() }

    fun digitOrWord(search: (List<String>) -> Pair<Int, String>, pick: BiPredicate<Int, Int>) : Char {
        val (digitPos, digit) = search(digits)
        val (wordPos, word) = search(words)
        return if (pick.test(digitPos, wordPos)) digit[0] else wordsToDigit[word]!!
    }

    fun part2(input: List<String>) = input.sumOf {
        val first = digitOrWord({strings -> it.findAnyOf(strings) ?: Pair(it.length, "")},
            { digitPos, wordPos -> digitPos < wordPos } )

        val last = digitOrWord({strings -> it.findLastAnyOf(strings) ?: Pair(-1, "")},
            { digitPos, wordPos -> digitPos > wordPos } )

        calibrate(first, last)
    }

    // Part 1 test
    val testInput1 = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent().lines()

    check(part1(testInput1) == 142)

    // Part 2 test
    val testInput2 = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent().lines()

    check(part2(testInput2) == 281)

    // Day 1 solutions
    val input = readInput("Day01")
    check(part1(input) == 54644)
    check(part2(input) == 53348)
}