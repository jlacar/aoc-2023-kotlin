fun mergeContiguous(ranges: List<LongRange>): List<LongRange> = ranges
    .sortedBy { it.first }
    .fold(mutableListOf()) { merged, thisRange ->
        if (merged.isEmpty() || thisRange.first > merged.last().last + 1) {
            merged.add(thisRange)
        } else {
            val contiguous = LongRange(merged.last().first, thisRange.last)
            merged.removeLast()
            merged.add(contiguous)
        }
        merged
    }

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

    fun convertRange(valuesToConvert: LongRange): List<LongRange> {
        val unconverted = mutableListOf(valuesToConvert)
        val converted: MutableList<LongRange> = mutableListOf()
        allConverted@ for (source in sourceRanges.withIndex()) {
            applesauce(source, unconverted, converted)
            if (unconverted.isEmpty()) break@allConverted
        }
        if (unconverted.isNotEmpty()) converted.addAll(unconverted)
        return converted
    }

    private fun applesauce(
        source: IndexedValue<LongRange>,
        unconverted: MutableList<LongRange>,
        converted: MutableList<LongRange>
    ) {
        val (index, sourceRange) = source
        val convertible = unconverted.firstOrNull { canConvert(sourceRange, it) }
        if (convertible != null) {
            val firstConvertibleValue = Math.max(sourceRange.first, convertible.first)
            val lastConvertibleValue = Math.min(sourceRange.last, convertible.last)
            val firstDestination = destinationRanges[index].elementAt(sourceRange.indexOf(firstConvertibleValue))
            val lastDestination = destinationRanges[index].elementAt(sourceRange.indexOf(lastConvertibleValue))
            converted.add(LongRange(firstDestination, lastDestination))
            if (convertible.first < firstConvertibleValue) unconverted.add(
                LongRange(
                    convertible.first,
                    firstConvertibleValue - 1
                )
            )
            if (convertible.last > lastConvertibleValue) unconverted.add(
                LongRange(
                    lastConvertibleValue + 1,
                    convertible.last
                )
            )
            unconverted.remove(convertible)
        }
    }

    private fun canConvert(sourceRange: LongRange, values: LongRange) =
        values.first <= sourceRange.last && values.last >= sourceRange.first

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

private fun List<AlmanacMapping>.convert(values: List<Long>): List<Long> =
    fold(values.toMutableList()) { sourceValues, mapping ->
        sourceValues.map { mapping.convert(it) }.toMutableList()
    }

private fun List<AlmanacMapping>.convertRanges(values: List<LongRange>): List<LongRange> =
    fold(values.toMutableList()) { sourceValues, mapping ->
        println("mapping ${mapping.source} to ${mapping.destination}")
        println("values  $sourceValues")
        mergeContiguous(sourceValues.map { mapping.convertRange(it) }.flatten()).toMutableList()
    }

class Day05(val seeds: List<Long>, val almanac: List<AlmanacMapping>) {
    private val seedRanges: List<LongRange> = seeds.chunked(2) { (start, length) ->
            LongRange(start, start + length - 1)
        }

    fun part1(): Long = almanac.convert(seeds).min()

    fun part2(): Long = almanac.convertRanges(seedRanges).minOf { it.first }

    companion object {

        fun using(input: List<String>) = Day05(
                seeds = seedsFrom(input.first()),
                almanac = almanacFrom(input)
            )

        private fun seedsFrom(line: String) = line.substringAfter(": ").asListOfLong(" ")

        private fun almanacFrom(input: List<String>): List<AlmanacMapping> {
            val mappings = mutableListOf<AlmanacMapping>()
            var nextMapStartIndex = 2
            while (nextMapStartIndex < input.lastIndex) {
                mappings.add(AlmanacMapping.parse(
                    input.subList(nextMapStartIndex, input.size)
                        .takeWhile { it.isNotBlank() }
                        .also { nextMapStartIndex += (it.size + 1) }
                    )
                )
            }
            return mappings.toList()
        }
    }
}

fun main() {

//    AlmanacMapping.parse(
//        """
//        seed-to-soil map:
//        3788621315 24578909 268976974
//        0 24569000 5000
//        """.trimIndent().lines()
//    ).apply {
//        convertRange(LongRange(24568909, 24568909 + 5000000)).println()
//    }
//

    // Test parsing to an AlmanacMapping
    AlmanacMapping.parse(
        """
        seed-to-soil map:
        50 98 2
        52 50 48            
        """.trimIndent().lines()
    ).apply {
        val expected = listOf<Long>(81, 14, 57, 13)
        listOf<Long>(79, 14, 55, 13).forEachIndexed() {  i, source ->
            val actual = convert(source)
            check(expected[i] == actual) {
                lazyMessage("Almanac test", expected[i], actual)
            }
        }
    }

    // Test one small mapping conversion
    Day05.using(
        """
            seeds: 98 65 68 99
            
            seed-to-soil map:
            50 98 2
            52 50 48
        """.trimIndent().lines()
    ).apply {
        val expected: Long = 50
        val actual = part1()
        check(expected == actual) {
            lazyMessage("Part 1 (simple)", expected, actual)
        }

        val expected2: Long = 50
        val actual2 = part2()
        check(expected2 == actual2) {
            lazyMessage("Part 2 (simple)", expected2, actual2, this.almanac)
        }
    }

//    check(false) { "Temp break!!!" }

    // Test conversion of sample data from problem
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
        val expected1: Long = 35
        val actual1 = part1()
        check( expected1 == actual1 ) {
            lazyMessage("Part 1 sample data", expected1, actual1)
        }

        val expected2: Long = 46
        val actual2 = part2()
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
        val correctAnswer: Long = 600279879
        val actual = part1().also { "Part 1 -> $it".println() }

        check(actual == correctAnswer) {
            lazyMessage("Broke Part 1!!!", correctAnswer, actual)
        }
    }

    // Part 2
    Day05.using(readInput("Day05")).apply {
        val correctAnswer: Long = 20191102
        val actual = part2().also { "Part 2 -> $it".println() }

        check(actual == correctAnswer) {
            lazyMessage("Broke Part 2!!!", correctAnswer, actual)
        }
    }

    "That's it!".println()
}
