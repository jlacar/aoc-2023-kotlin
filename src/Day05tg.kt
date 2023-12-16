/* Day 5 - Attempt to think like Todd Ginsburg, now that I've seen his solution */

class Day05tg(val seeds: List<Long>, val conversionTables: List<ConversionTable>) {

    fun part1(): Long = 0L

    fun part2(): Long = 0L

    companion object {
        fun using(input: List<String>) = Day05tg (
            parseSeeds(input),
            parseConversionTables(input)
        )

        private fun parseConversionTables(input: List<String>): List<ConversionTable> {
            TODO("Not yet implemented")
        }

        private fun parseSeeds(input: List<String>): List<Long> {
            TODO("Not yet implemented")
        }
    }
}

class ConversionTable {

}

fun main() {
    // Test using sample data from the problem
    Day05tg.using(
        """
        seeds: 79 14 55 13
        
        seed-to-soil map:
        50 98 2
        52 50 48
        
        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15
        
        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4
        
        water-to-light map:
        88 18 7
        18 25 70
        
        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13
        
        temperature-to-humidity map:
        0 69 1
        1 0 69
        
        humidity-to-location map:
        60 56 37
        56 93 4        
        """.trimIndent().lines()
    ).apply {
        val actual1 = part1()
        val expected1: Long = 35
        check( expected1 == actual1 ) {
            lazyMessage("Part 1 sample data", expected1, actual1)
        }

        val actual2 = part2()
        val expected2: Long = 46
        check(expected2 == actual2) {
            lazyMessage("Part 2 sample data", expected2, actual2)
        }
    }

    check(false) {
        """
        |
        | All tests PASS! To see problem solution:
        | - Set the flag in this check to true
        | - Remove or disable .also() debugs calls
        """.trimMargin()
    }

    "SOLUTION".println()

    // Part 1
    Day05.using(readInput("Day05")).apply {
        val actual = part1().also { "Part 1 -> $it".println() }
        val correctAnswer: Long = 600279879

        check(actual == correctAnswer) {
            lazyMessage("Broke Part 1!!!", correctAnswer, actual)
        }
    }

    // Part 2
    Day05.using(readInput("Day05")).apply {
        val actual = part2().also { "Part 2 -> $it".println() }
        val correctAnswer: Long = 20191102

        check(actual == correctAnswer) {
            lazyMessage("Broke Part 2!!!", correctAnswer, actual)
        }
    }
}