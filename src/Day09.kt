typealias History = List<Int>
typealias Histories = List<History>

class Day09(private val histories: Histories) : AoCSolution() {

    override val description = "Day 9: Mirage Maintenance"

    override fun part1(): Int = histories.sumOf { it.extrapolateNext() }

    override fun part2(): Int = histories.sumOf { it.reversed().extrapolateNext() }

    companion object {
        fun using(input: List<String>) = Day09(input.map { it.toInts() })
    }

    // Extension functions, for readability
    private fun History.differencesBetweenSteps() = windowed(2).map { (left, right) -> right - left }
    private fun History.isAllZero() = all { it == 0 }

    private fun History.extrapolateNext(): Int {
        tailrec fun next(sequence: History, acc: Int = 0): Int {
            val nextNumber = acc + sequence.last()
            val newSequence = sequence.differencesBetweenSteps()
            return if (newSequence.isAllZero()) nextNumber else next(newSequence, nextNumber)
        }
        return next(this)
    }
}

fun main() {
    val doneWithTDD  = true   // TODO toggle this as needed

    val simpleSequence =
        """
        6 9 12 15
        """.trimIndent().lines()

    SolutionChecker(Day09.using(simpleSequence), "simple sequence").apply {
        checkAnswerForPartOneIs(18)
        checkAnswerForPartTwoIs(3)
    }

    val mainExample =
        """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45            
        """.trimIndent().lines()

    SolutionChecker(Day09.using(mainExample), "main example").apply {
        checkAnswerForPartOneIs(18 + 28 + 68)
        checkAnswerForPartTwoIs(-3 + 0 + 5)
    }

    // TODO temporary breakpoint to aid testing; edit and move around as needed
    check(doneWithTDD) {
        lazyMessage("\n^^^^^^^^^ IGNORE ^^^^^^^^^\nTests PASSED!",
            "RED",
            "GREEN",
            "Now Refactor!"
        )
    }

    "SOLUTION".println()

    SolutionChecker(Day09.using(readInput("Day09")), "Google").apply {
        checkAnswerForPartOneIs(1_974_232_246)
        checkAnswerForPartTwoIs(928)
    }

    SolutionChecker(Day09.using(readInput("Day09gh")), "GitHub").apply {
        checkAnswerForPartOneIs(1_666_172_641)
        checkAnswerForPartTwoIs(933)
    }

    "That's it!".println()
}