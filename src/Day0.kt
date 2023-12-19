// TODO update this header
/**
 * --- Day 0: Solution Template for Advent of Code ---
 */

// TODO Ctrl + Cmd + G - selects all occurrences for editing
class Day0() {

    fun part1(): Int = -1

    fun part2(): Int = -1

    companion object {
        fun using(input: List<String>) = Day0()
    }
}

fun main() {
    val runAllTests  = true    // TODO toggle this as needed
    val runSolutions = true    // TODO toggle this as needed

    // TODO temporary breakpoint to aid testing; edit and move around as needed
    check(runAllTests) {
        lazyMessage("Scenario: ", -1, -1, "Extra info")
    }

    val sampleInput1 =
        """
        *** PASTE SAMPLE HERE
        """.trimIndent().lines()

    Day0.using(sampleInput1).apply {
        with (part1()) {
            "Part 1 (example 1) -> $this".println()

            // TODO update this
            val expected = -1
            check(this == expected) {
                lazyMessage("Part 1 (example 1)", expected, this)
            }
        }
    }

    val sampleInput2 =
        """
        *** PASTE SAMPLE HERE
        """.trimIndent().lines()

    Day0.using(sampleInput2).apply {
        with (part1()) {
            "Part 1 (example 2) -> $this".println()

            // TODO update this
            val expected = -1
            check(this == expected) {
                lazyMessage("Part 1 (example 2)", expected, this)
            }
        }
    }

    val sampleInputPart2 =
        """
        *** PASTE SAMPLE HERE
        """.trimIndent().lines()

    Day0.using(sampleInputPart2).apply {
        with (part2()) {
            "Part 2 (sample) -> $this".println()

            // TODO update this when Part 2 is revealed
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
        | - Set the runSolutions flag to true
        | - Remove or disable .also() debugs calls
        """.trimMargin()
    }

    "SOLUTION".println()

    // TODO update the input file name
    val myPuzzleInput = readInput("DayXX")

    Day0.using(myPuzzleInput).apply {
        with (part1()) {
            "Part 1 -> $this".println()

            // TODO update this when you find the correct answer
            val correctAnswer = -1
            check(this == correctAnswer) {
                lazyMessage("You broke Part 1!", correctAnswer, this)
            }
        }

        with (part2()) {
            "Part 2 -> $this".println()

            // TODO update this when you find the correct answer
            val correctAnswer = -1
            check(this == correctAnswer) {
                lazyMessage("You broke Part 2!", correctAnswer, this)
            }
        }
    }

    "That's it!".println()
}