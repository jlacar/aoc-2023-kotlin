
fun main() {

    // PART 1

    fun String.firstDigit(): Char = (this.firstOrNull { it.isDigit() } ?: '0')

    fun String.lastDigit(): Char = (this.lastOrNull { it.isDigit() } ?: '0')

    fun calibration1(s: String) = s.firstDigit().digitToInt() * 10 + s.lastDigit().digitToInt()

    fun part1(input: List<String>): Int {
        return input.sumOf { calibration1(it) }
    }

    // PART 2

    val digitNames = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun locateFirst(digitName: String, s: String): Pair<String, Int> {
        val pos = s.indexOf(digitName)
        return Pair(digitName, if (pos < 0) s.length else pos)
    }

    fun firstDigitName(s: String): Pair<String, Int> =
        if (s.length < 3) Pair("zero", s.length)
        else digitNames.map { locateFirst(it, s) }.minBy { it.second }

    fun firstDigitPart2(s: String): Int {
        val firstDigit = s.firstDigit()
        val firstName = firstDigitName(if (firstDigit == '0') s else s.substringBefore(firstDigit))
        return if (firstDigit == '0' || firstName.second < s.indexOf(firstDigit)) digitNames.indexOf(firstName.first)
               else firstDigit.digitToInt()
    }

    fun locateLast(digitName: String, s: String): Pair<String, Int> = Pair(digitName, s.lastIndexOf(digitName))

    fun lastDigitName(s: String): Pair<String, Int> {
        if (s.length < 3) return Pair("zero", -1)
        return digitNames.map { locateLast(it, s) }.maxBy { it.second }
    }

    fun secondDigitPart2(s: String): Int {
        val lastDigit = s.lastDigit()
        val lastName = lastDigitName(if (lastDigit == '0') s else s.substringAfterLast(lastDigit))
        return if (lastName.second < 0) lastDigit.digitToInt() else digitNames.indexOf(lastName.first)
    }

    fun calibration2(s: String): Int {
        return firstDigitPart2(s) * 10 + secondDigitPart2(s)
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { calibration2(it) }
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
