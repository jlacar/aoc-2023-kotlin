/**
 * Template for Advent of Code solutions using objects/classes
 */

data class CandidatePalindrome(val value: String) {
    fun isPalindrome(perfect: Boolean = false): Boolean =
        if (perfect) isPerfect(value) else isBasic()

    private fun isPerfect(s: String): Boolean =
        s.reversed() == s

    private fun isBasic() = isPerfect(value.filter { it.isLetter() }.lowercase())

    companion object {
        fun of(input: String): CandidatePalindrome {
            return CandidatePalindrome(input)
        }

        fun parseToList(input: List<String>): List<CandidatePalindrome> {
            return input.map { of(it) }
        }
    }
}

class Day0(val values: List<CandidatePalindrome>) {
    companion object {
        fun using(input: List<String>) = Day0(
                CandidatePalindrome.parseToList(input)
            )
    }

    fun part1(): Int = values.count { it.isPalindrome() }

    fun part2(): Int = values.count { it.isPalindrome(perfect = true) }
}

fun main() {

    // Small tests (for TDD)
    Day0.using("""
        not a palindrome
        a
        aba
        Madam I'm Adam
    """.trimIndent().lines()).apply {

        val expected1 = 3                        // uncomment to see the actual value
        val actual1 = part1()                    // .also { "Part 1 (simplest) -> $it".println() }

        check(expected1 == actual1) {
            lazyMessage("Part 1 (simplest)", expected1, actual1)
        }

        val expected2 = 2
        val actual2 = part2()

        check(expected2 == actual2) {
            lazyMessage("Part 2 (simplest)", expected2, actual2)
        }
    }

    val testInputFromAoC = """
        Madam I'm Adam 
        Too bad—I hid a boot
        too bad dab oot
        not a palindrome
    """.trimIndent().lines()

    Day0.using(testInputFromAoC).apply {
        val expected1 = 3
        val actual1 = part1()

        check( expected1 == actual1 ) {
            lazyMessage("Part 1 (example)", expected1, actual1)
        }

        val expected2 = 1
        val actual2 = part2()

        check( expected2 == actual2 ) {
            lazyMessage("Part 2 (example)", expected2, actual2)
        }
    }

//    check(false) { "All tests above PASS so far" }

    // Gate to solution:

    check(true) {
        """
        |
        | All tests PASS! To see the answers:
        | - Set the flag in this gate check to true
        | - Remove or disable .also() debugs calls
        """.trimMargin()
    }

    "SOLUTION".println()

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