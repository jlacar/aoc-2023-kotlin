/* Day 5 - Attempt to think like Todd Ginsburg, now that I've seen his solution */

class Day05tg(val seeds: List<Long>, private val conversionTables: List<ConversionTable>) {

    val seedRanges: List<LongRange> = seeds.chunked(2) { (first, length) ->
        first..<(first + length)
    }

    fun part1(): Long = seeds.minOf { convert(it) }

    fun part2(): Long {
        val reverseLookup = reversedConversionTable()
        return generateSequence(0, Long::inc).first { location ->
            val seed = convert(location, reverseLookup)
            seedRanges.any { it.contains(seed) }
        }
    }

    private fun reversedConversionTable(): List<ConversionTable> = conversionTables.reversed()
        .map { it.reversed() }

    private fun convert(seed: Long, conversions: List<ConversionTable> = conversionTables): Long =
        conversions.fold(seed) { source: Long, lookupTable: ConversionTable ->
            lookupTable.convert(source)
        }

    companion object {
        fun using(input: List<String>) = Day05tg (
            parseSeeds(input),
            parseConversionTables(input)
        )

        private fun parseSeeds(input: List<String>): List<Long> =
            input.first().substringAfter(": ").asListOfLong(" ")

        private fun parseConversionTables(input: List<String>): List<ConversionTable> =
            input.drop(2)
                .joinToString("\n").split("\n\n")
                .map { table -> ConversionTable.parse(table.lines()) }
    }
}

data class ConversionTable(val description: String, val conversions: List<Conversion>) {
    fun convert(value: Long): Long = conversions.firstOrNull { it.contains(value) } ?.convert(value) ?: value
    fun reversed() = ConversionTable("reversed", conversions.map { it.reversed() })

    companion object {
        fun parse(conversions: List<String>) = ConversionTable (
            conversions.first(),
            conversions.drop(1).map { Conversion.parse(it) }
        )
    }
}

data class Conversion(val destination: LongRange, val source: LongRange) {
    fun convert(sourceValue: Long): Long =
        if (sourceValue in source) destination.first + sourceValue - source.first else sourceValue

    fun contains(value: Long) = source.contains(value)

    fun reversed() = Conversion(source, destination)

    companion object {
        fun parse(input: String): Conversion {
            val (destStart, sourceStart, length) = input.asListOfLong(" ")
            return Conversion(
                destStart..<(destStart + length),
                sourceStart..<(sourceStart + length)
            )
        }
    }
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
            lazyMessage("Part 1 sample data", expected1, actual1, "seeds: $seeds")
        }

        val actual2 = part2()
        val expected2: Long = 46
        check(expected2 == actual2) {
            lazyMessage("Part 2 sample data", expected2, actual2)
        }
    }

    check(true) {
        """
        |
        | All tests PASS! To see problem solution:
        | - Set the flag in this check to true
        | - Remove or disable .also() debugs calls
        """.trimMargin()
    }

    "SOLUTION".println()

    // Part 1
    Day05tg.using(readInput("Day05")).apply {
        val actual = part1().also { "Part 1 -> $it".println() }
        val correctAnswer: Long = 600279879

        check(actual == correctAnswer) {
            lazyMessage("Broke Part 1!!!", correctAnswer, actual)
        }
    }

    // Part 2
    Day05tg.using(readInput("Day05")).apply {
        val actual = part2().also { "Part 2 -> $it".println() }
        val correctAnswer: Long = 20191102

        check(actual == correctAnswer) {
            lazyMessage("Broke Part 2!!!", correctAnswer, actual)
        }
    }
}