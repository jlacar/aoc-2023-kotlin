fun main() {

    // DSL extensions
    fun String.firstDigit() = this.firstOrNull { it.isDigit() } ?: '0'
    fun String.lastDigit() = this.lastOrNull { it.isDigit() } ?: '0'

    fun calibrate(first: Char, last: Char) = first.digitToInt() * 10 + last.digitToInt()

    // PART 1

    fun part1(input: List<String>) = input.sumOf {
        calibrate(it.firstDigit(), it.lastDigit())
    }

    // PART 2

    val words = "one two three four five six seven eight nine".split(" ")
    val digits = "123456789".map { it.toString() }
    val wordsToDigit = words.zip(digits.map { it.first() }).toMap()

    // DSL extensions
    fun String.firstOfAny(strings: List<String>) = findAnyOf(strings) ?: Pair(length, "")
    fun String.lastOfAny(strings: List<String>) = findLastAnyOf(strings) ?: Pair(-1, "")

    fun String.firstDigitOrWord(): Char {
        val (digitPos, digit) = firstOfAny(digits)
        val (wordPos, word) = firstOfAny(words)
        return if (digitPos < wordPos) digit[0] else wordsToDigit[word] ?: '0'
    }

    fun String.lastDigitOrWord(): Char {
        val (digitPos, digit) = lastOfAny(digits)
        val (wordPos, word) = lastOfAny(words)
        return if (digitPos > wordPos) digit[0] else wordsToDigit[word] ?: '0'
    }

    fun part2(input: List<String>) = input.sumOf {
        calibrate(it.firstDigitOrWord(), it.lastDigitOrWord())
    }

    // Part 1 test
    val testInput1 = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        **badinput**
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
        **badinput**
        7pqrstsixteen
    """.trimIndent().lines()

    check(part2(testInput2) == 281)

    // Day 1 solutions
    val input = readInput("Day01")

    check( 54_644 ==
        part1(input).also { "Part 1 -> $it".println() }
    )

    check( 53_348 ==
        part2(input).also { "Part 2 -> $it".println() }
    )

    val gitHubInput = readInput("Day01gh")

    check( 55_538 ==
        part1(gitHubInput).also { "Part 1 github -> $it".println() }
    )

    check( 54_875 ==
        part2(gitHubInput).also { "Part 2 -> $it".println() }
    )

    "Solved it!".println()
}