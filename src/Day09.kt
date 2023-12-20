/**
 * --- Day 9: Mirage Maintenance ---
 */

typealias History = List<Int>
typealias Histories = List<History>

class Day09(val histories: Histories) {

    fun part1(): Int = histories.sumOf { it.extrapolateNext() }

    fun part2(): Int = histories.sumOf { it.reversed().extrapolateNext() }

    companion object {
        fun using(input: List<String>) = Day09(input.map { it.toInts() })
    }

    private fun History.differencesBetweenSteps() = windowed(2).map { (left, right) -> right - left }

    private fun History.extrapolateNext(): Int {
        tailrec fun nextIn(sequence: History, acc: Int = 0): Int {
            val newSequence = sequence.differencesBetweenSteps()
            return if (newSequence.all { it == 0 }) acc + last()
                   else nextIn(newSequence, acc + newSequence.last())
        }
        return nextIn(this)
    }
}

fun main() {

    val doneWithTDD  = true   // TODO toggle this as needed
    val runSolutions = true   // TODO toggle this as needed

    val simpleSequence =
        """
        3 6 9 12
        """.trimIndent().lines()

    Day09.using(simpleSequence).apply {
        with (part1()) {
            "Part 1 (simple sequences) -> $this".println()

            val expected = 15
            check(this == expected) {
                lazyMessage("Part 1 (simple sequences)", expected, this)
            }
        }

        with (part2()) {
            "Part 2 (simple sequences) -> $this".println()

            val expected = 0
            check(this == expected) {
                lazyMessage("Part 2 (simple sequences)", expected, this)
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
                lazyMessage("Part 1 (main example)", expected, this)
            }
        }

        with (part2()) {
            "Part 2 (main example) -> $this".println()

            val expected = -3 + 0 + 5
            check(this == expected) {
                lazyMessage("Part 1 (main example)", expected, this)
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

            val correctAnswer = 1974232246
            check(this == correctAnswer) {
                lazyMessage("You broke Part 1!", correctAnswer, this)
            }
        }

        with (part2()) {
            "Part 2 -> $this".println()

            val correctAnswer = 928
            check(this == correctAnswer) {
                lazyMessage("You broke Part 2!", correctAnswer, this)
            }
        }
    }

    "That's it!".println()
}