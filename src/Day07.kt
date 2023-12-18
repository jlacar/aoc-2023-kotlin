/**
 * --- Day 7: Camel Cards ---
 */

class Day07(private val plays: List<CamelCardPlay>) {

    fun part1(): Int = totalWinnings(plays.sortedWith( compareBy { it.normalStrength } ))

    fun part2(): Int = totalWinnings(plays.sortedWith( compareBy { it.jokerStrength } ))

    private fun totalWinnings(rankedPlays: List<CamelCardPlay>): Int =
        rankedPlays.mapIndexed { rank, play -> (rank + 1) * play.bid }.sum()

    companion object {
        fun using(input: List<String>) = Day07(
            plays = input.map {
                val (hand, bid) = it.split(" ")
                CamelCardPlay(hand, bid.toInt())
            }
        )
    }
}

enum class HandType {
    HIGH_CARD, ONE_PAIR, TWO_PAIRS, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND;

    val strength: Char = 'A' + this.ordinal

    companion object {
        fun of(hand: String): HandType {
            val distinctRanks = hand.charFrequencies()
            return when (distinctRanks.count { it.value != 0 }) {
                1 -> FIVE_OF_A_KIND
                2 -> if (distinctRanks.any { it.value == 4 }) FOUR_OF_A_KIND else FULL_HOUSE
                3 -> if (distinctRanks.any { it.value == 3 }) THREE_OF_A_KIND else TWO_PAIRS
                4 -> ONE_PAIR
                else -> HIGH_CARD
            }
        }
    }
}

data class CamelCardPlay(val hand: String, val bid: Int) {

    private val countOf = hand.charFrequencies()

    private val jokerHand = if ((countOf['J'] ?: 0) == 0) hand else hand.replace('J', mostNotJ())

    private fun mostNotJ() = countOf.filter { it.key != 'J' }.maxByOrNull { it.value }?.key ?: 'A'

    val normalStrength = strength(HandType.of(hand).strength, hand, normalRules)
    val jokerStrength = strength(HandType.of(jokerHand).strength, hand, jokerRules)

    companion object {
        val normalRules = strengthOrder("23456789TJQKA")
        val jokerRules = strengthOrder("J23456789TQKA")

        private fun strengthOrder(rankOrder: String): Map<Char, Char> =
            mutableMapOf<Char, Char>().apply {
                rankOrder.zip("ABCDEFGHIJKLM") { ch, strength -> this[ch] = strength }
            }

        fun strength(typeStrength: Char, hand: String, strengthOf: Map<Char, Char>) =
            hand.fold(typeStrength.toString()) { acc, ch -> acc + strengthOf[ch] }
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
            val correctAnswer = 250825971

            check(this == correctAnswer) {
                lazyMessage("You broke Part 2!", correctAnswer, this)
            }
        }
    }

    "That's it!".println()
}