/**
 * Day 6: Wait For It
 */

data class RaceSheet(val allowedTimes: List<Int>, val recordDistances: List<Int>) {

    fun waysToWin(): List<Int> =
        allowedTimes.foldIndexed(mutableListOf<Int>()) {index, waysToWin, timeAllowed ->
            waysToWin.add(distancesFor(timeAllowed).count { it > recordDistances[index] })
            waysToWin
        }

    private fun distancesFor(timeAllowed: Int) =
        IntRange(1, timeAllowed - 1).map { holdFor -> holdFor * (timeAllowed - holdFor) }

    companion object {
        fun parse(input: List<String>): RaceSheet {
            val (times, distances) = input.subList(0, 2)
            return RaceSheet(
                allowedTimes = times.substringAfter(":").trim().asListOfInt(" "),
                recordDistances = distances.substringAfter(":").trim().asListOfInt(" ")
            )
        }
    }
}

class Day6(val raceSheet: RaceSheet) {
    companion object {
        fun using(input: List<String>) = Day6(
                RaceSheet.parse(input)
            )
    }

    fun part1(): Int = productOfAll(raceSheet.waysToWin())

    fun part2(): Int = 0
}

fun main() {

    // Small tests (for TDD)
    Day6.using("""
       Time:      7
       Distance:  9
    """.trimIndent().lines()).apply {
        with (part1()) {
            val expected = 4
            check(this == expected) { lazyMessage("Part 1 (simplest)", expected, this) }
        }

        with (part2()) {
            val expected = 0
            check(this == expected) { lazyMessage("Part 2 (simplest)", expected, this) }
        }
    }

    val testInputFromAoC = """
            Time:      7  15   30
            Distance:  9  40  200
            """.trimIndent().lines()

    Day6.using(testInputFromAoC).apply {
        val expected1 = 288
        val actual1 = part1()

        check( expected1 == actual1 ) {
            lazyMessage("Part 1 (example)", expected1, actual1)
        }

        val expected2 = 0
        val actual2 = part2()

        check( expected2 == actual2 ) {
            lazyMessage("Part 2 (example)", expected2, actual2)
        }
    }

//    check(false) { "All tests above PASS so far" }

    // Gate to solution:

    check(true) {
        """
        |
        | All tests PASS! To see the answers:
        | - Set the flag in this gate check to true
        | - Remove or disable .also() debugs calls
        """.trimMargin()
    }

    "SOLUTION".println()

    val inputDay6 = readInput("Day06")

    // Part 1
    Day6.using(inputDay6).apply {
        val correctAnswer = 316_800
        val actual = part1().also { "Part 1 --> $it".println() }

        check(actual == correctAnswer) {
            lazyMessage("You broke Part 1!", correctAnswer, actual)
        }
    }

    // Part 2
    Day6.using(inputDay6).apply {
        val correctAnswer = 0
        val actual = part2()

        check(actual == correctAnswer) {
            lazyMessage("You broke Part 2!", correctAnswer, actual)
        }
    }

    "That's it!".println()
}