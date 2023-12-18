/**
 * Day 6: Wait For It
 */

class Day6(val times: List<Int>, val recordDistances: List<Long>) {
    private data class Race(val timeAllowed: Int, val recordDistance: Long) {
        fun waysToWin(): Int = (1..<timeAllowed).count { time ->
            time.toLong() * (timeAllowed - time) > recordDistance
        }
    }

    // PART 1

    fun part1(): Int = part1Races.productOfAllWaysToWin()

    private fun List<Race>.productOfAllWaysToWin(): Int = fold(1) { product, race -> product * race.waysToWin() }

    private val part1Races: List<Race> = times.zip(recordDistances) { t: Int, d: Long ->
        Race(timeAllowed = t, recordDistance = d)
    }

    // PART 2

    fun part2(): Int = part2Race.waysToWin()

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

    Day6.using(testInputFromAoC).apply {
        val expected1 = 288
        val actual1 = part1()

        check(expected1 == actual1) {
            lazyMessage("Part 1 (example)", expected1, actual1)
        }

        val expected2 = 71503
        val actual2 = part2()

        check(expected2 == actual2) {
            lazyMessage("Part 2 (example)", expected2, actual2)
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

    val myPuzzleInput = readInput("Day06")

    Day6.using(myPuzzleInput).apply {
        val actual1 = part1().also { "Part 1 --> $it".println() }
        val correctAnswer1 = 316_800

        check(actual1 == correctAnswer1) {
            lazyMessage("You broke Part 1!", correctAnswer1, actual1)
        }

        val actual2 = part2().also { "Part 2 --> $it".println() }
        val correctAnswer2 = 45647654

        check(actual2 == correctAnswer2) {
            lazyMessage("You broke Part 2!", correctAnswer2, actual2)
        }
    }

    "That's it!".println()
}