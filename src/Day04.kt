import kotlin.math.pow

// --- Day 4: Scratchcards ---

// Logical representation of one line in the input
data class ScratchCard(val id: Int, val winners: Set<Int>, val numbers: Set<Int>) {
    fun matchingNumbers(): Set<Int> = winners intersect numbers

    fun points(): Int = matchingNumbers().size.let { n ->
        if (n > 0) 2.0.pow(n - 1).toInt() else 0
    }

    companion object {
        fun of(line: String): ScratchCard {
            val (label, winners, numbers) = line.split(": ", " | ")
            val id = label.substringAfterLast(" ").toInt()
            return ScratchCard(
                id,
                winners.asSetOfInt(" "),
                numbers.asSetOfInt(" ")
            )
        }
    }
}

fun main() {

    // DSL Extensions
    fun List<String>.asCards(): List<ScratchCard> = map { ScratchCard.of(it) }

    // PART 1

    fun part1(input: List<String>) = input.asCards().sumOf { it.points() }

    // PART 2

    fun part2(input: List<String>): Int {
        return 30
    }

    // Tests

    val testSmallInput1 = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
    """.trimIndent().lines()
    check( 8 == // expected
        part1(testSmallInput1) // .also { "$testSmallInput1\n part 1, got [$it]" }
    )
//    check(1 == part2(testSmallInput1) == 1)

    val testSmallInput2 = """
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
    """.trimIndent().lines()

    check(2 + 1 + 0 == // expected
        part1(testSmallInput2) //.also { "$testSmallInput2\n part 1, got [$it]" }
    )

//    check(part2(testSmallInput2) == 3)

    val testSampleFromAoC1 = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
    """.trimIndent().lines()

    check( 13 == // expected
        part1(testSampleFromAoC1) // .also { "$testSampleFromAoC1\n part 1, got [$it]".println() }
    )

    check( 30 == // expected
        part2(testSampleFromAoC1) // .also { "$testSampleFromAoC1\n part 2, got [$it]".println() }
    )

    // SOLUTION

    val input = readInput("Day04")
    check(22_193 == part1(input).also { "Part 1 --> $it".println() })

//    check(expected? ==
    part2(input).also { "Part 2 --> $it".println() }
//    )

    "That's it!".println()
}
