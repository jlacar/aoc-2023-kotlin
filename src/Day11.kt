class Day11() : AoCSolution() {
    override val description = "Day 11: Cosmic Expansion"

    override fun part1(): Int = -1

    override fun part2(): Int = -1

    companion object {
        fun using(input: List<String>) = Day11()
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

    SolutionChecker(Day11.using(sampleInput1), "sampleInput1").apply {
        checkAnswerForPartOneIs(-1)
        checkAnswerForPartTwoIs(-1)
    }

    val sampleInput2 =
        """
        *** PASTE SAMPLE HERE
        """.trimIndent().lines()

    SolutionChecker(Day11.using(sampleInput2), "sampleInput2").apply {
        checkAnswerForPartOneIs(-1)
        checkAnswerForPartTwoIs(-1)
    }

    "SOLUTION".println()

    SolutionChecker(Day11.using(readInput("Day11")), "Google").apply {
        checkAnswerForPartOneIs(-1)
        checkAnswerForPartTwoIs(-1)
    }

    SolutionChecker(Day11.using(readInput("Day11gh")), "GitHub").apply {
        checkAnswerForPartOneIs(-1)
        checkAnswerForPartTwoIs(-1)
    }

    "That's it!".println()
}