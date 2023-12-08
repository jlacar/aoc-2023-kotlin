import kotlin.math.pow

// --- Day 4: Scratchcards ---

// Logical representation of one line in the input
data class ScratchCard(val id: Int, val points: Int, val matchingNumbers: Int) {
    companion object {
        fun parse(line: String): ScratchCard {
            val (label, winners, numbers) = line.split(": ", " | ")
            val id = label.substringAfterLast(" ").toInt()
            val matchingNumbers = (
                    winners.asSetOfInt(" ") intersect numbers.asSetOfInt(" ")
                ).size

            return ScratchCard(
                id,
                points = if (matchingNumbers > 0) 2.0.pow(matchingNumbers - 1).toInt() else 0,
                matchingNumbers
            )
        }
    }
}

fun main() {

    // DSL Extensions
    fun List<String>.asCards(): List<ScratchCard> = map { ScratchCard.parse(it) }

    // PART 1

    fun part1(input: List<String>) = input.asCards().sumOf { it.points }

    // PART 2

    fun initializeCardCounts(originalStackSize: Int) =
        buildMap {
            for (i in 1..originalStackSize) {
                put(i, 1)
            }
        }.toMutableMap()

    fun List<ScratchCard>.processWins(): List<Int> {
        val cardCounts = initializeCardCounts(size)
        forEach { card ->
            val additionalCards = cardCounts[card.id] ?: 0
            for (i in card.id + 1..minOf(size, card.id + card.matchingNumbers)) {
                cardCounts[i] = cardCounts[i]!!.plus(additionalCards)
            }
        }
        return cardCounts.values.toList()
    }

    fun part2(input: List<String>): Int =
        input               //.also { "input         -> $it".println() }
          .asCards()        //.also { "asCards()     -> $it".println() }
          .processWins()    //.also { "processWins() -> $it".println() }
          .sum()

    // Tests

    val testSmallInput1 = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
    """.trimIndent().lines()

    check(8 ==
        part1(testSmallInput1)
    ) { "You broke it!" }

    val testSmallInput2 = """
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
    """.trimIndent().lines()

    check(2 + 1 + 0 ==
        part1(testSmallInput2)
    ) { "You broke it!" }

    val testSampleFromAoC1 = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
    """.trimIndent().lines()

    check(13 ==
        part1(testSampleFromAoC1)
    ) { "You broke it!" }

    val testPart2SmallSample1 = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
    """.trimIndent().lines()

    var expected = 3
    var actual = part2(testPart2SmallSample1)
    check(expected == actual) {
        """
            FAILED Part 2 
            input -> $testPart2SmallSample1
            expected [$expected]
            but got  [$actual]
        """.trimIndent()
    }

    expected = 30
    actual = part2(testSampleFromAoC1)
    check(expected == actual) {
        """
            FAILED Part 2 
            input -> $testSampleFromAoC1
            expected [$expected]
            but got  [$actual]
        """.trimIndent()
    }

    // If you get to this point, you're ready for a solution run;
    // change to false if debugging or refactoring
    check(true) { "All tests PASSED! Disable all debug .also before solution run!"}

    // SOLUTION

    val input = readInput("Day04")
    check(22_193 ==
        part1(input).also { "Part 1 --> $it".println() }
    ) { "You broke it!" }

    check(5_625_994 ==
        part2(input).also { "Part 2 --> $it".println() }
    ) { "You broke it!" }

    "That's it!".println()
}

