/**
 * --- Day 7: Camel Cards ---
 */

class Day07(private val bets: List<CamelCardsBet>) {

    fun part1(): Int = totalWinnings(bets.sortedWith( compareBy { it.normalStrength() } ))

    fun part2(): Int = totalWinnings(bets.sortedWith( compareBy { it.jokerStrength() } ))

    private fun totalWinnings(rankedBets: List<CamelCardsBet>): Int =
        rankedBets.mapIndexed { i, bet -> (i + 1) * bet.bid }.sum()

    private fun rankedBets2(): List<CamelCardsBet> =
        listOf(
            CamelCardsBet("32T3K", 765),
            CamelCardsBet("KK677", 28),
            CamelCardsBet("T55J5", 684),
            CamelCardsBet("QQQJA", 483),
            CamelCardsBet("KTJJT", 220)
        )

    companion object {
        fun using(input: List<String>): Day07 {
            val bets = input.map {
                val (hand, bid) = it.split(" ")
                CamelCardsBet(hand, bid.toInt())
            }
            return Day07(bets)
        }
    }
}

enum class HandType {
    HIGH_CARD, ONE_PAIR, TWO_PAIRS, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND;

    val strength: Char = 'A' + this.ordinal

    companion object {
        fun of(hand: String): HandType {
            val cardCounts = hand.charFrequencies()
            return when (cardCounts.count { it.value != 0 }) {
                1 -> FIVE_OF_A_KIND
                2 -> if (cardCounts.any { it.value == 4 }) FOUR_OF_A_KIND else FULL_HOUSE
                3 -> if (cardCounts.any { it.value == 3 }) THREE_OF_A_KIND else TWO_PAIRS
                4 -> ONE_PAIR
                else -> HIGH_CARD
            }
        }
    }
}

data class CamelCardsBet(val hand: String, val bid: Int) {
    fun normalStrength(): String {
        val strengthOf = mutableMapOf<Char, Char>().apply {
            "23456789TJQKA".zip("ABCDEFGHIJKLM") { ch, strength -> this[ch] = strength }
        }
        val type: HandType = HandType.of(hand)
        return hand.fold(type.strength.toString()) {acc, ch -> acc + strengthOf[ch] }
    }

    fun jokerStrength(): String {
        return normalStrength()
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

//        with (part2()) {
//            "Part 2 (sample) -> $this".println()
//
//            val expected = 5905
//            check(this == expected) {
//                lazyMessage("Part 2 (example)", expected, this)
//            }
//        }
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