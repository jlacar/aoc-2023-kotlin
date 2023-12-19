/**
 * --- Day 9: Mirage Maintenance ---
 */

class Day09() {

    fun part1(): Int = -1

    fun part2(): Int = -1

    companion object {
        fun using(input: List<String>) = Day09()
    }
}

fun main() {

    val runAllTests  = true    // TODO toggle this as needed
    val runSolutions = false   // TODO toggle this as needed

    val history1 =
        """
        0 3 6 9 12 15
        """.trimIndent().lines()

    Day09.using(history1).apply {
        with (part1()) {
            "Part 1 (history 1) -> $this".println()

            val expected = 18
            check(this == expected) {
                lazyMessage("Part 1 (example 1)", expected, this)
            }
        }
    }

    // TODO temporary breakpoint to aid testing; edit and move around as needed
    check(runAllTests) {
        lazyMessage("Scenario: ", -1, -1, "Extra info")
    }

    val history2 =
        """
        1 3 6 10 15 21
        """.trimIndent().lines()

    Day09.using(history2).apply {
        with (part1()) {
            "Part 1 (history 2) -> $this".println()

            val expected = 28
            check(this == expected) {
                lazyMessage("Part 1 (example 1)", expected, this)
            }
        }
    }

    val history3 =
        """
        10 13 16 21 30 45            
        """.trimIndent().lines()

    Day09.using(history3).apply {
        with (part1()) {
            "Part 1 (history 3) -> $this".println()

            val expected = 68
            check(this == expected) {
                lazyMessage("Part 1 (example 1)", expected, this)
            }
        }
    }

    val mainExample =
        """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45            
        """.trimIndent().lines()

    Day09.using(mainExample).apply {
        with (part1()) {
            "Part 1 (main example) -> $this".println()

            val expected = 18 + 28 + 68
            check(this == expected) {
                lazyMessage("Part 1 (example 2)", expected, this)
            }
        }
    }

    val sampleInputPart2 =
        """
        """.trimIndent().lines()

    Day09.using(sampleInputPart2).apply {
        with (part2()) {
            "Part 2 (sample) -> $this".println()

            val expected = -1
            check(this == expected) {
                lazyMessage("Part 2 (example)", expected, this)
            }
        }
    }

    check(runSolutions) {
        """
        |
        | All tests PASS! To see the answers:
        | - Set the flag to true
        | - Remove or disable .also() debugs calls
        """.trimMargin()
    }

    "SOLUTION".println()

    val myPuzzleInput = readInput("Day09")

    Day09.using(myPuzzleInput).apply {
        with (part1()) {
            "Part 1 -> $this".println()

            val correctAnswer = -1
            check(this == correctAnswer) {
                lazyMessage("You broke Part 1!", correctAnswer, this)
            }
        }

        with (part2()) {
            "Part 2 -> $this".println()

            val correctAnswer = -1
            check(this == correctAnswer) {
                lazyMessage("You broke Part 2!", correctAnswer, this)
            }
        }
    }

    "That's it!".println()
}