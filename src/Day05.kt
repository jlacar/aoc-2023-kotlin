data class AlmanacMapping(val destination: String,
                          val destinationRanges: List<LongRange>,
                          val source: String,
                          val sourceRanges: List<LongRange>) {
    fun convert(source: Long): Long = sourceRanges.indexOfFirst { it.contains(source) }
        .let { rangeIndex ->
            if (rangeIndex == -1) {
                source
            } else {
                destinationRanges[rangeIndex].first() + source - sourceRanges[rangeIndex].first()
            }
        }

    companion object {
        fun parse(input: List<String>): AlmanacMapping {
            val (destination, source) = parseNames(input.first())
            val (destinationRanges, sourceRanges) =
                parseRanges(input.subList(1, input.size))

            return AlmanacMapping( destination, destinationRanges, source, sourceRanges)
        }

        private fun parseNames(input: String) = Pair(
            input.substringAfter("-to-").substringBefore(" map:"),
            input.substringBefore("-to-")
        )

        private fun parseRanges(input: List<String>): Pair<MutableList<LongRange>, MutableList<LongRange>> {
            val destinationRanges = mutableListOf<LongRange>()
            val sourceRanges = mutableListOf<LongRange>()
            input.forEach { line ->
                val (destinationStart, sourceStart, rangeLength) = line.asListOfLong(" ")
                destinationRanges.add(LongRange(destinationStart, destinationStart + rangeLength - 1))
                sourceRanges.add(LongRange(sourceStart, sourceStart + rangeLength - 1))
            }
            return Pair(destinationRanges, sourceRanges)
        }
    }
}

private fun List<AlmanacMapping>.convert(values: List<Long>, source: String, destination: String): List<Long> {
    "Converting $values from $source to $destination".println()
    return foldIndexed(values.toMutableList()) { _, sources, almanacMapping ->
        sources.fold(mutableListOf<Long>()) { destinationValues, sourceValue ->
            destinationValues.add(almanacMapping.convert(sourceValue))
            destinationValues
        }
    }
}

class Day05(val seeds: List<Long>, val almanac: List<AlmanacMapping>) {
    fun part1(): Long = almanac
        .convert(seeds, "seeds", "location")              // .also { "converted to $it".println() }
        .min()

    companion object {
        fun using(input: List<String>) = Day05(
                seeds = seedsFrom(input.first()),
                almanac = almanacFrom(input.subList(3, input.size))
            )

        private fun seedsFrom(line: String) = line.substringAfter(": ").asListOfLong(" ")

        private fun almanacFrom(input: List<String>): List<AlmanacMapping> {
            // TODO -- realize this faked implementation
            return listOf(
                AlmanacMapping(
                    "soil",
                    listOf(LongRange(50, 52), LongRange(52, 100)),
                    "seed",
                    listOf(LongRange(98, 100), LongRange(60, 98))
                )
            )
        }
    }
}

fun main() {

    check(LongRange(0, -1).isEmpty()) { "Range is NOT empty!" }

    AlmanacMapping.parse(
        """
        seed-to-soil map:
        50 98 2
        52 50 48            
        """.trimIndent().lines()
    ).apply {
        this.println()

        val expected = listOf<Long>(81, 14, 57, 13)
        listOf<Long>(79, 14, 55, 13).forEachIndexed() {  i, source ->
            val actual = convert(source)
            check(expected[i] == actual) {"expected ${expected[i]} but got [$actual]"}
        }
    }

    Day05.using(
        """
            seeds: 98 65 68 99
            
            seed-to-soil map:
            50 98 2
            52 50 48
        """.trimIndent().lines()
    ).apply {
        "seeds: $seeds".println()
        "almanac: $almanac".println()

        val actual = part1()                                // TODO still faking this part out
        check(false) { "Got part1() == $actual" }
    }

    Day05.using(
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
        "seeds -> $seeds".println()

        val expected1: Long = 35
        val actual1 = part1()               .also { "part1(sample) -> $it".println() }
        check( expected1 == actual1 ) {
            """FAILED part 1 with sample data
                | expected   [$expected1]
                | but got    [$actual1]
            """.trimMargin()
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

    Day05.using(readInput("Day05")).apply {
        "Part 1 -> ${part1()}".println()
    }

    "That's it!".println()
}