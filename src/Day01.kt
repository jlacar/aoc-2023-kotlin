
fun main() {

    // PART 1

    fun String.firstDigit(): Char = (this.firstOrNull { it.isDigit() } ?: '0')

    fun String.lastDigit(): Char = (this.lastOrNull { it.isDigit() } ?: '0')

    fun part1(input: List<String>): Int {
        return input.sumOf { it.firstDigit().digitToInt() * 10 + it.lastDigit().digitToInt() }
    }

    // PART 2

    data class NameOffset(val name: String, val offset: Int)

    val digitNames = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun locateFirst(digitName: String, s: String): NameOffset {
        val pos = s.indexOf(digitName)
        return NameOffset(digitName, if (pos < 0) s.length else pos)
    }

    fun firstDigitName(s: String): NameOffset =
        if (s.length < 3) NameOffset("zero", s.length)
        else digitNames.map { locateFirst(it, s) }.minBy { it.offset }

    fun firstDigitPart2(s: String): Int {
        val firstDigit = s.firstDigit()
        val firstName = firstDigitName(if (firstDigit == '0') s else s.substringBefore(firstDigit))
        return if (firstDigit == '0' || firstName.offset < s.indexOf(firstDigit)) digitNames.indexOf(firstName.name)
               else firstDigit.digitToInt()
    }

    fun lastDigitName(s: String): NameOffset {
        if (s.length < 3) return NameOffset("zero", -1)
        return digitNames.map { NameOffset(it, s.lastIndexOf(it)) }.maxBy { it.offset }
    }

    fun lastDigitPart2(s: String): Int {
        val numeral = s.lastDigit()
        val lastWord = lastDigitName(if (numeral == '0') s else s.substringAfterLast(numeral))
        return if (lastWord.offset < 0) numeral.digitToInt() else digitNames.indexOf(lastWord.name)
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { firstDigitPart2(it) * 10 + lastDigitPart2(it) }
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
