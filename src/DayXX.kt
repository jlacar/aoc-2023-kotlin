// Having a data class can help organize code and logic
// data class DomainConcept(val prop1, val prop2) {
//
//      companion object {
//          fun of(input: String): DomainConcept {
//              return DomainConcept(..)
//          }
//      }
// }

fun main() {

    // DSL Extensions

    // PART 1

    fun part1(input: List<String>): Int {
        return input.size
    }

    // PART 2

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Tests
    "Long.MAX_VALUE = ${Long.MAX_VALUE}".println()

    val testSmallInput1 = """
        small.input.sample
    """.trimIndent().lines()
    check(part1(testSmallInput1) == 1)
    check(part2(testSmallInput1) == 1)

    val testSmallInput2 = """
        small.input.sample
        sample.small.input
    """.trimIndent().lines()
    check(part1(testSmallInput2) == 2)
    check(part2(testSmallInput2) == 2)

    val testSampleFromAoC1 = """
        paste.aoc.sample.here
    """.trimIndent().lines()

    // Debug
    val expected = 2
    val actual = part1(testSampleFromAoC1)
    check(expected == actual) {
        """
            FAILED Part 1 
            input -> $testSampleFromAoC1
            expected [$expected]
            but got  [$actual]
        """.trimIndent()
    }

    val testSampleFromAoC2 = """
        paste.aoc.sample.here
    """.trimIndent().lines()
    check(part2(testSampleFromAoC2) == 1)

    // If you get to this point, you're ready for a solution run
    check(false) { "All tests PASSED! Disable all debug .also before solution run!"}

    // SOLUTION

    val input = readInput("DayXX")

//    check(expected? ==
    part1(input).also { "Part 1 --> $it".println() }
//    )

//    check(expected? ==
    part2(input).also { "Part 2 --> $it".println() }
//    )

    "That's it!".println()
}
