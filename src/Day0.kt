// TODO Ctrl + Cmd + G - selects all occurrences for editing
class Day0() : AoCSolution() {
    override val description = "Solution Template for Advent of Code in Kotlin"

    override fun part1(): Int = -1

    override fun part2(): Int = -1

    companion object {
        fun using(input: List<String>) = Day0()
    }
}

fun main() {
    val doneWithTDD = false     // TODO toggle this as needed

    // TODO temporary breakpoint to aid testing; edit and move around as needed
    check(doneWithTDD) {
        lazyMessage("\n^^^^^^^^^ IGNORE ^^^^^^^^^\nTests PASSED!",
            "RED",
            "GREEN",
            "Now Refactor!"
        )
    }

    val sampleInput1 =
        """
        *** PASTE SAMPLE HERE
        """.trimIndent().lines()

    SolutionChecker(Day0.using(sampleInput1), "sampleInput1").apply {
        checkAnswerForPartOneIs(-1)
        checkAnswerForPartTwoIs(-1)
    }

    val sampleInput2 =
        """
        *** PASTE SAMPLE HERE
        """.trimIndent().lines()

    SolutionChecker(Day0.using(sampleInput2), "sampleInput2").apply {
        checkAnswerForPartOneIs(-1)
        checkAnswerForPartTwoIs(-1)
    }

    "SOLUTION".println()

    SolutionChecker(Day0.using(readInput("DayXX")), "Google").apply {
        checkAnswerForPartOneIs(-1)
        checkAnswerForPartTwoIs(-1)
    }

    SolutionChecker(Day0.using(readInput("DayXXgh")), "GitHub").apply {
        checkAnswerForPartOneIs(-1)
        checkAnswerForPartTwoIs(-1)
    }

    "That's it!".println()
}