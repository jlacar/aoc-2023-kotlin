/**
 * --- Day 7: Camel Cards ---
 */

class Day07(val bets: List<CamelCardsBet>) {

    fun part1(): Int = rankedBets().mapIndexed { i, bet -> (i + 1) * bet.bid }.sum()

    private fun rankedBets(): List<CamelCardsBet> {
        return bets;
    }

    fun part2(): Int = 0

    companion object {
        fun using(input: List<String>): Day07 {
            val bets = input.map {
                val (hand, bets) = it.split(" ")
                CamelCardsBet(hand, bets.toInt())
            }
            return Day07(bets)
        }
    }
}

data class CamelCardsBet(val hand: String, val bid: Int)

fun main() {

    val tempInput =
        """
        32T3K 765
        KTJJT 220
        KK677 28
        T55J5 684
        QQQJA 483
        """.trimIndent().lines()

    Day07.using(tempInput).apply {
        val actual = part1()
        val expected = 6440

        check(expected == actual) {
            lazyMessage("Temp BREAKPOINT", expected, actual)
        }
    }
    check(false) {"Temp BREAKPOINT"}

    val sampleInput =
        """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
        """.trimIndent().lines()

    // TODO update the class
    Day07.using(sampleInput).apply {
        with (part1()) {
            "Part 1 (sample) -> $this".println()

            val expected = 6440
            check(this == expected) {
                lazyMessage("Part 1 (example)", expected, this)
            }
        }

        with (part2()) {
            "Part 2 (sample) -> $this".println()
            // TODO update this
            val expected = -1
            check(this == expected) {
                lazyMessage("Part 2 (example)", expected, this)
            }
        }
    }

    // TODO toggle this to true to see answers, false to stop here
    check(false) {
        """
        |
        | All tests PASS! To see the answers:
        | - Set the flag in this gate check to true
        | - Remove or disable .also() debugs calls
        """.trimMargin()
    }

    "SOLUTION".println()

    val myPuzzleInput = readInput("Day07")

    // Part 1
    Day07.using(myPuzzleInput).apply {
        with (part1()) {
            "Part 1 -> $this".println()
            val correctAnswer = 0  // TODO update this

            check(this == correctAnswer) {
                lazyMessage("You broke Part 1!", correctAnswer, this)
            }
        }

        with (part2()) {
            "Part 2 -> $this".println()
            val correctAnswer = 0  // TODO update this

            check(this == correctAnswer) {
                lazyMessage("You broke Part 2!", correctAnswer, this)
            }
        }
    }

    "That's it!".println()
}