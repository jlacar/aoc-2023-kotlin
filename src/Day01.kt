
fun main() {

    // PART 1

    fun String.firstDigit(): Char = (this.firstOrNull { it.isDigit() } ?: '0')

    fun String.lastDigit(): Char = (this.lastOrNull { it.isDigit() } ?: '0')

    fun part1(input: List<String>): Int {
        return input.sumOf { it.firstDigit().digitToInt() * 10 + it.lastDigit().digitToInt() }
    }

    // PART 2

    data class WordOffset(val name: String, val offset: Int)

    val digitWords = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun locateFirst(word: String, s: String): WordOffset =
        s.indexOf(word).let { WordOffset(word, if (it < 0) s.length else it) }

    fun firstDigitWord(s: String): WordOffset =
        if (s.length < 3) WordOffset(digitWords[0], s.length)
        else digitWords.map { locateFirst(it, s) }.minBy { it.offset }

    fun firstDigit2(s: String): Int = s.firstDigit().let { numeral ->
        val firstWord = firstDigitWord(if (numeral == '0') s else s.substringBefore(numeral))
        if (numeral == '0' || s.indexOf(numeral) > firstWord.offset) digitWords.indexOf(firstWord.name)
        else numeral.digitToInt()
    }

    fun lastDigitWord(s: String) =
        if (s.length < 3) WordOffset(digitWords[0], -1)
        else digitWords.map { word -> WordOffset(word, s.lastIndexOf(word)) }.maxBy { it.offset }

    fun lastDigit2(s: String): Int = s.lastDigit().let { numeral ->
        val lastWord = lastDigitWord(if (numeral == '0') s else s.substringAfterLast(numeral))
        if (lastWord.offset < 0) numeral.digitToInt() else digitWords.indexOf(lastWord.name)
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { firstDigit2(it) * 10 + lastDigit2(it) }
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
