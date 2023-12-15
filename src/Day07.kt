/**
 * --- Day 7: Camel Cards ---
 */

// TODO change this name
data class CamelCardHand(val value: String, val bid: Int) {
    fun winnings(): Int {
        return bid
    }

    // Helper functions

    companion object {
        fun parseToList(input: List<String>): List<CamelCardHand> =
            input.map { line ->
                val (hand, bid) = line.split(" ")
                CamelCardHand(hand, bid.toInt())
            }
    }
}

// TODO Change Day name and signature
class Day7(val hands: List<CamelCardHand>) {
    companion object {
        fun using(input: List<String>) = Day7(CamelCardHand.parseToList(input))
    }

    // TODO update this
    fun part1(): Int = hands.sumOf { it.winnings() }

    // TODO update this
    fun part2(): Int = hands.count()
}

fun main() {

    // TODO change this data
    // Small tests (for TDD)
    Day0.using("""
        not a palindrome
        a
        aba
        Madam I'm Adam
    """.trimIndent().lines()).apply {

        with(part1()) {
            // TODO update this
            val expected = 3
            check(this == expected) {
                lazyMessage("Part 1 (simplest)", expected, this)
            }
        }

        with(part2()) {
            // TODO update this
            val expected = 2
            check(this == expected) {
                lazyMessage("Part 2 (simplest)", expected, this)
            }
        }
    }

    // TODO copy-paste sample from problem
    val testInputFromAoC = """
        Madam I'm Adam 
        Too bad—I hid a boot
        too bad dab oot
        not a palindrome
    """.trimIndent().lines()

    // TODO update the class
    Day0.using(testInputFromAoC).apply {
        with (part1()) {
            // TODO update this
            val expected = 3
            check(this == expected) {
                lazyMessage("Part 1 (example)", expected, this)
            }
        }

        with (part2()) {
            // TODO update this
            val expected = 1
            check(this == expected) {
                lazyMessage("Part 2 (example)", expected, this)
            }
        }
    }

    // TODO uncomment and move this to TDD
//    check(false) { "All tests above PASS" }

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

    val puzzleInput = readInput("DayXX") // TODO update this

    // Part 1
    // TODO update which day this is
    Day0.using(puzzleInput).apply {
        with (part1()) {
            // TODO set to bogus value, then update with correct answer
            val correctAnswer = 3

            "Part 1 -> $this".println()
            check(this == correctAnswer) {
                lazyMessage("You broke Part 1!", correctAnswer, this)
            }
        }
    }

    // Part 2
    // TODO update which day this is
    Day0.using(puzzleInput).apply {
        with (part2()) {
            // TODO set to bogus value, then update with correct answer
            val correctAnswer = 1

            "Part 2 -> $this".println()
            check(this == correctAnswer) {
                lazyMessage("You broke Part 2!", correctAnswer, this)
            }
        }
    }

    "That's it!".println()
}