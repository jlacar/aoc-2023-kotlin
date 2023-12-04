fun main() {

    fun calibrate(first: Char, last: Char) = first.digitToInt() * 10 + last.digitToInt()

    fun String.firstDigit(): Char = this.firstOrNull { it.isDigit() } ?: '0'

    fun String.lastDigit(): Char = this.lastOrNull { it.isDigit() } ?: '0'

    // PART 1

    fun part1(input: List<String>) = input.sumOf {
        calibrate(it.firstDigit(), it.lastDigit())
    }

    // PART 2

    val digits = "123456789".map { it.toString() }

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

    fun String.firstOfAny(strings: List<String>) = findAnyOf(strings) ?: Pair(length, "")
    fun String.lastOfAny(strings: List<String>) = findLastAnyOf(strings) ?: Pair(-1, "")

    fun String.firstDigitOrWord(): Char {
        val (digitPos, digit) = firstOfAny(digits)
        val (wordPos, word) = firstOfAny(words)
        return if (digitPos < wordPos) digit[0] else wordsToDigit[word]!!
    }

    fun String.lastDigitOrWord(): Char {
        val (digitPos, digit) = lastOfAny(digits)
        val (wordPos, word) = lastOfAny(words)
        return if (digitPos > wordPos) digit[0] else wordsToDigit[word]!!
    }

    fun part2(input: List<String>) = input.sumOf {
        calibrate(it.firstDigitOrWord(), it.lastDigitOrWord())
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