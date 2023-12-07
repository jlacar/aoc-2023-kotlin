import kotlin.math.pow

// --- Day 4: Scratchcards ---

// Having a data class can help organize code and logic
data class ScratchCard(val winning: Set<Int>, val cardNumbers: Set<Int>) {
    fun points(): Int {
        val n = (winning intersect cardNumbers).size
        return if (n == 0) 0 else 2.0.pow(n - 1).toInt()
    }
    companion object {
        fun of(line: String): ScratchCard {
            val (_, ws, cns) = line.split(": ", " | ")
            val winning = ws.asSetOfInt(" ")
            val cardNumbers = cns.asSetOfInt(" ")
            return ScratchCard(winning, cardNumbers)
        }
    }
}

fun String.asSetOfInt(vararg delimiters: String): Set<Int> =
    this.split(*delimiters).filter { it.isNotBlank() }.map { it.toInt() }.toSet()

fun main() {

    // DSL Extensions
    fun List<String>.asCards(): List<ScratchCard> {
        return map { ScratchCard.of(it) }
    }

    // PART 1

    fun part1(input: List<String>) = input.asCards().sumOf { it.points() }

    // PART 2

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Tests

    val testSmallInput1 = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
    """.trimIndent().lines()
    check(part1(testSmallInput1) == 8)
    check(part2(testSmallInput1) == 1)

    val testSmallInput2 = """
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
    """.trimIndent().lines()

    check(part1(testSmallInput2) == 2 + 1 + 0)
    check(part2(testSmallInput2) == 3)

    val testSampleFromAoC1 = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().lines()

    check(part1(testSampleFromAoC1)
            == 13)

    val testSampleFromAoC2 = """
        paste.aoc.sample.here
    """.trimIndent().lines()
    check(part2(testSampleFromAoC2) == 1)

    // Day X Solution
    val input = readInput("Day04")

    check(part1(input) == 22_193)
//    "Part 1 --> ${part1(input)}".println()

//    check(part2(input) == ???).also { it.println() }
    "Part 2 --> ${part2(input)}".println()

    "That's it!".println()
}
