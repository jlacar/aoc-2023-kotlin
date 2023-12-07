// Having a data class can help organize code and logic
// data class DomainConcept(val prop1, val prop2) {
//
//      companion object {
//          fun of(..) {
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
    check(part1(testSampleFromAoC1) == 1)

    val testSampleFromAoC2 = """
        paste.aoc.sample.here
    """.trimIndent().lines()
    check(part2(testSampleFromAoC2) == 1)

    // Day X Solution
    val input = readInput("DayXX")

//    check(part1(input) == ???).also { it.println() }
    "Part 1 --> ${part1(input)}".println()

//    check(part2(input) == ???).also { it.println() }
    "Part 2 --> ${part2(input)}".println()

    "That's it!".println()
}
