/**
 * --- Day 7: Camel Cards ---
 */

class Day07(val bets: List<CamelCardsBet>) {

    fun part1(): Int = rankedBets().mapIndexed { i, bet -> (i + 1) * bet.bid }.sum()

    private fun rankedBets(): List<CamelCardsBet> {
        return bets.sortedWith( compareBy { it.strength } );
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

enum class HandType {
    HIGH_CARD, ONE_PAIR, TWO_PAIRS, THREE_KIND, FULL_HOUSE, FOUR_KIND, FIVE_KIND;

    companion object {
        fun of(hand: String): HandType {
            val cardCounts = hand.charFrequencies()
            return when (cardCounts.count { it.value != 0 }) {
                1 -> FIVE_KIND
                2 -> if (cardCounts.any { it.value == 4 }) FOUR_KIND else FULL_HOUSE
                3 -> if (cardCounts.any { it.value == 3 }) THREE_KIND else TWO_PAIRS
                4 -> ONE_PAIR
                else -> HIGH_CARD
            }
        }
    }
}

enum class CamelCard {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

    companion object {
        fun of(ch: Char): CamelCard =
            entries.getOrNull("23456789TJQKA".indexOf(ch)) ?: error("Invalid card")
    }
}

fun String.charFrequencies(): Map<Char, Int> {
    val charCounts = mutableMapOf<Char, Int>()
    forEach { ch -> charCounts[ch] = charCounts.getOrDefault(ch, 0) + 1 }
    return charCounts
}

data class CamelCardsBet(val hand: String, val bid: Int) {
    private val type: HandType = HandType.of(hand)
    val strength: String = encode()

    private fun encode(): String {
        val cardValues = "23456789TJQKA"
        val codes      = "ABCDEFGHIJKLM"
        return hand.fold(codes[type.ordinal].toString()) {acc: String, ch: Char ->
            acc + codes[cardValues.indexOf(ch)]
        }
    }
}

fun main() {

    val sampleInput =
        """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
        """.trimIndent().lines()

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

            val expected = 5905
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

    val myPuzzleInput = readInput("Day07")

    // Part 1
    Day07.using(myPuzzleInput).apply {
        with (part1()) {
            "Part 1 -> $this".println()
            val correctAnswer = 251216224

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