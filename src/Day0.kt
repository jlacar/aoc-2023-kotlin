/**
 * Template for Advent of Code solutions using objects/classes
 */

// TODO change this name
data class CandidatePalindrome(val value: String) {

    // TODO delete or replace these functions
    fun isPalindrome(perfect: Boolean = false): Boolean =
        if (perfect) isPerfect(value) else isBasic()

    private fun isPerfect(s: String): Boolean =
        s.reversed() == s

    private fun isBasic() = isPerfect(value.filter { it.isLetter() }.lowercase())

    companion object {
        // TODO change how it's parsed
        fun of(input: String): CandidatePalindrome {
            return CandidatePalindrome(input)
        }

        fun parseToList(input: List<String>): List<CandidatePalindrome> {
            return input.map { of(it) }
        }
    }
}

// TODO Change Day name and signature
class Day0(val values: List<CandidatePalindrome>) {
    companion object {

        // TODO update this
        fun using(input: List<String>) = Day0(
                CandidatePalindrome.parseToList(input)
            )
    }

    // TODO update this
    fun part1(): Int = values.count { it.isPalindrome() }

    // TODO update this
    fun part2(): Int = values.count { it.isPalindrome(perfect = true) }
}

fun main() {

    // TODO change this data
    // Small tests (for TDD)
    Day0.using("""
        not a palindrome
        a
        aba
        Madam I'm Adam
    """.trimIndent().lines()).apply {

        with(part1()) {
            // TODO update this
            val expected = 3
            check(this == expected) {
                lazyMessage("Part 1 (simplest)", expected, this)
            }
        }

        with(part2()) {
            // TODO update this
            val expected = 2
            check(this == expected) {
                lazyMessage("Part 2 (simplest)", expected, this)
            }
        }
    }

    // TODO copy-paste sample from problem
    val testInputFromAoC = """
        Madam I'm Adam 
        Too bad—I hid a boot
        too bad dab oot
        not a palindrome
    """.trimIndent().lines()

    // TODO update the class
    Day0.using(testInputFromAoC).apply {
        with (part1()) {
            // TODO update this
            val expected = 3
            check(this == expected) {
                lazyMessage("Part 1 (example)", expected, this)
            }
        }

        with (part2()) {
            // TODO update this
            val expected = 1
            check(this == expected) {
                lazyMessage("Part 2 (example)", expected, this)
            }
        }
    }

    // TODO uncomment this to TDD
//    check(false) { "All tests above PASS so far" }

    // Gate to solution:

    // TODO toggle this
    check(true) {
        """
        |
        | All tests PASS! To see the answers:
        | - Set the flag in this gate check to true
        | - Remove or disable .also() debugs calls
        """.trimMargin()
    }

    "SOLUTION".println()

    // TODO update this
    val inputDayXX = readInput("DayXX")

    // Part 1
    Day0.using(inputDayXX).apply {
        val correctAnswer = 3
        val actual = part1().also { "Part 1 -> $it".println() }

        check(actual == correctAnswer) {
            lazyMessage("You broke Part 1!", correctAnswer, actual)
        }
    }

    // Part 2
    Day0.using(inputDayXX).apply {
        val correctAnswer = 1
        val actual = part2().also { "Part 2 -> $it".println() }

        check(actual == correctAnswer) {
            lazyMessage("You broke Part 2!", correctAnswer, actual)
        }
    }

    "That's it!".println()
}