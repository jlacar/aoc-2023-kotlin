/**
 * --- Day 9: Mirage Maintenance ---
 */

typealias History = List<Int>
typealias Histories = List<History>

class Day09(val histories: Histories) {

    fun part1(): Int = histories.sumOf { it.extrapolateNext() }

    fun part2(): Int = -1

    companion object {
        fun using(input: List<String>) = Day09(input.map { it.toInts() })
    }

    private fun History.extrapolateNext(): Int {
        tailrec fun nextIn(sequence: History, acc: Int = 0): Int {
            val newSequence = sequence.windowed(2).map { (left, right) -> right - left }
            return if (newSequence.all { it == 0 }) acc + last()
                   else nextIn(newSequence, acc + newSequence.last())
        }
        return nextIn(this)
    }
}


fun main() {

    val doneWithTDD  = false   // TODO toggle this as needed
    val runSolutions = false   // TODO toggle this as needed

    val simpleSequence =
        """
        3 6 9 12
        """.trimIndent().lines()

    Day09.using(simpleSequence).apply {
        with (part1()) {
            "Part 1 (simple sequences) -> $this".println()

            val expected = 15
            check(this == expected) {
                lazyMessage("Part 1 (example 0)", expected, this)
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

    // TODO temporary breakpoint to aid testing; edit and move around as needed
    check(doneWithTDD) {
        lazyMessage("\n^^^^^^^^^ IGNORE ^^^^^^^^^\nTests PASSED!",
            "-",
            "-",
            "-"
        )
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