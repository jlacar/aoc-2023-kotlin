class Day6(val times: List<Int>, val recordDistances: List<Long>) : AoCSolution() {
    override val description: String
        get() = "Day 6: Wait For It"

    private data class Race(val timeAllowed: Int, val recordDistance: Long) {
        fun waysToWin(): Int = (1..<timeAllowed).count { time ->
            time.toLong() * (timeAllowed - time) > recordDistance
        }
    }

    // PART 1

    override fun part1(): Int = part1Races.productOfAllWaysToWin()

    private val part1Races: List<Race> = times.zip(recordDistances) { t: Int, d: Long ->
        Race(timeAllowed = t, recordDistance = d)
    }

    private fun List<Race>.productOfAllWaysToWin(): Int =
        fold(1) { product, race -> product * race.waysToWin() }

    // PART 2

    override fun part2(): Int = part2Race.waysToWin()

    private val part2Race = Race(
        timeAllowed = times.joinToString("").toInt(),
        recordDistance = recordDistances.joinToString("").toLong()
    )

    companion object {
        fun using(input: List<String>) = Day6 (
                times = input[0].substringAfter("Time: ").trim().toInts(),
                recordDistances = input[1].substringAfter("Distance: ").trim().toLongs()
            )
    }
}

fun main() {
    val testInputFromAoC = """
            Time:      7  15   30
            Distance:  9  40  200
            """.trimIndent().lines()

    SolutionChecker(Day6.using(testInputFromAoC), "test input").apply {
        checkAnswerForPartOneIs(288)
        checkAnswerForPartTwoIs(71503)
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

    SolutionChecker(Day6.using(readInput("Day06")), "(Google)").apply {
        checkAnswerForPartOneIs(316_800)
        checkAnswerForPartTwoIs(45_647_654)
    }

    SolutionChecker(Day6.using(readInput("Day06gh")), "(GitHub)").apply {
        checkAnswerForPartOneIs(393_120)
        checkAnswerForPartTwoIs(36_872_656)
    }

    "That's it!".println()
}