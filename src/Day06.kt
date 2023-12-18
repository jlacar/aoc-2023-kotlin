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

    private val part1Races: List<Race> = times.zip(recordDistances) { t: Int, d: Long ->
        Race(timeAllowed = t, recordDistance = d)
    }

    private fun List<Race>.productOfAllWaysToWin(): Int =
        fold(1) { product, race -> product * race.waysToWin() }

    // Could also be (but I like the above better: I think it's easier to read
//    fun part1(): Int = times.zip(recordDistances) { t: Int, d: Long ->
//            Race(timeAllowed = t, recordDistance = d)
//        }.fold(1) { product, race -> product * race.waysToWin() }

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
        with (part1()) {
            "Part 1 (example) --> $this".println()

            val expected = 288
            check(this == expected) {
                lazyMessage("Part 1 (example)", expected, this)
            }
        }

        with (part2()) {
            "Part 2 (example) --> $this".println()

            val expected = 71503
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

    val myPuzzleInput = readInput("Day06")

    Day6.using(myPuzzleInput).apply {
        with (part1()) {
            "Part 1 --> $this".println()

            val correctAnswer = 316_800
            check(this == correctAnswer) {
                lazyMessage("You broke Part 1!", correctAnswer, this)
            }
        }

        with (part2()) {
            "Part 2 --> $this".println()

            val correctAnswer = 45647654
            check(this == correctAnswer) {
                lazyMessage("You broke Part 2!", correctAnswer, this)
            }
        }
    }

    "That's it!".println()
}