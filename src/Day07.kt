/**
 * --- Day 7: Camel Cards ---
 */

class Day07(val bets: List<CamelCardsBet>) {

    fun part1(): Int = rankedBets().mapIndexed { i, bet -> (i + 1) * bet.bid }.sum()

    private fun rankedBets(): List<CamelCardsBet> {
        return bets.sortedWith( compareBy { it.strength } );
    }

    fun winner(hand1: String, hand2: String): CamelCardsBet {
        val bet1 = CamelCardsBet(hand1, 0)
        val bet2 = CamelCardsBet(hand2, 0)
        return if (bet1.strength > bet2.strength) bet1 else bet2
    }

    fun listHandTypes() {
        bets.forEach { "${it.hand} -> ${HandType.of(it.hand)}".println() }
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

//    val tddInput = """
//        AAAAA 6
//        TAAAA 5
//        AATAA 5
//        AATTT 7
//        ATATA 7
//        TTTAA 8
//        A456A 8
//        KKKQT 9
//        32A23 9
//        TKA23 10
//    """.trimIndent().lines()
//
//    Day07.using(tddInput).apply {
//        val card1 = CamelCardsBet("22222", 0)
//        val card2 = CamelCardsBet("AAAAK", 0)
//        val winner = if (card1.strength > card2.strength) card1 else card2
//        check(winner == card1) { "WRONG: $card1 (${card1.strength}) should be greater than $card2 (${card2.strength})" }
//        check(false) {"Temp BREAKPOINT"}
//    }
//
//    val tempInput =
//        """
//        32T3K 765
//        KTJJT 220
//        KK677 28
//        T55J5 684
//        QQQJA 483
//        """.trimIndent().lines()
//
//    Day07.using(tempInput).apply {
//        val actual = part1()
//        val expected = 6440
//
//        check(expected == actual) {
//            lazyMessage("Temp BREAKPOINT", expected, actual)
//        }
//    }
//
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

//        with (part2()) {
//            "Part 2 (sample) -> $this".println()
//            // TODO update this
//            val expected = -1
//            check(this == expected) {
//                lazyMessage("Part 2 (example)", expected, this)
//            }
//        }
    }

    // TODO toggle this to true to see answers, false to stop here
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