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

    // TODO uncomment and move this to TDD
//    check(false) { "All tests above PASS" }

    // TODO toggle this to true to see answers, false to stop here
    check(true) {
        """
        |
        | All tests PASS! To see the answers:
        | - Set the flag in this gate check to true
        | - Remove or disable .also() debugs calls
        """.trimMargin()
    }

    "SOLUTION".println()

    val puzzleInput = readInput("DayXX") // TODO update this

    // Part 1
    // TODO update which day this is
    Day0.using(puzzleInput).apply {
        with (part1()) {
            // TODO set to bogus value, then update with correct answer
            val correctAnswer = 3

            "Part 1 -> $this".println()
            check(this == correctAnswer) {
                lazyMessage("You broke Part 1!", correctAnswer, this)
            }
        }
    }

    // Part 2
    // TODO update which day this is
    Day0.using(puzzleInput).apply {
        with (part2()) {
            // TODO set to bogus value, then update with correct answer
            val correctAnswer = 1

            "Part 2 -> $this".println()
            check(this == correctAnswer) {
                lazyMessage("You broke Part 2!", correctAnswer, this)
            }
        }
    }

    "That's it!".println()
}