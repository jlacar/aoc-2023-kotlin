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

    val sampleInput1 =
        """
        """.trimIndent().lines()

    Day09.using(sampleInput1).apply {
        with (part1()) {
            "Part 1 (example 1) -> $this".println()

            val expected = -1
            check(this == expected) {
                lazyMessage("Part 1 (example 1)", expected, this)
            }
        }
    }

    val sampleInput2 =
        """
        """.trimIndent().lines()

    Day09.using(sampleInput2).apply {
        with (part1()) {
            "Part 1 (example 2) -> $this".println()

            val expected = -1
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

    check(true) {
        """
        |
        | All tests PASS! To see the answers:
        | - Set the flag in this gate check to true
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