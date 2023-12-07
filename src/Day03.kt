data class SchematicNumber(val value: Int, val neighborIndices: Set<Int>) {
    fun isNearAny(symbolIndices: List<Int>) = (symbolIndices intersect neighborIndices).isNotEmpty()

    fun isNear(index: Int): Boolean = (listOf(index) intersect neighborIndices).isNotEmpty()

    companion object {
        fun of(line: String, indices: List<Int>): SchematicNumber {
            val start = indices.first()
            val end = indices.last() + 1
            val value = line.substring(start, end).toInt()
            return SchematicNumber(value, listOf(start-1, end) union indices)
        }
    }
}

data class Gear(val ratio: Int) {}

fun main() {

    // DSL Extensions
    fun Char.isSymbol() = this != '.' && !isDigit()
    fun Char.isGearSymbol() = this == '*'

    fun List<String>.sections() = windowed(3)

    // PART 1

    fun symbolIndices(section: List<String>): List<Int> =
        section.fold(mutableSetOf<Int>()) { locations, line ->
            line.forEachIndexed { index, ch -> if (ch.isSymbol()) locations.add(index) }
            locations
        }.toList()

    fun isNewGroup(index: Int, groups: MutableList<MutableList<Int>>) =
        groups.isEmpty() || groups.last().last() != index - 1

    fun addDigitIndex(index: Int, groups: MutableList<MutableList<Int>>) {
        if (isNewGroup(index, groups)) {
            groups.add(mutableListOf(index))
        } else {
            groups.last().add(index)
        }
    }

    fun digitIndicesOn(line: String): List<MutableList<Int>> =
        line.foldIndexed(mutableListOf()) { i, digitIndices, ch ->
            if (ch.isDigit()) {
                addDigitIndex(i, digitIndices)
            }
            digitIndices
        }

    fun numbersOn(line: String) =
        digitIndicesOn(line).fold(mutableListOf<SchematicNumber>()) { numbers, indices ->
            numbers.add(SchematicNumber.of(line, indices))
            numbers
        }

    fun partNumbersInSecondLineOf(section: List<String>) : List<SchematicNumber> {
        val symbolIndices = symbolIndices(section)
        return numbersOn(section[1]).filter {
            it.isNearAny(symbolIndices)
        }
    }

    fun part1(schematic: List<String>) = schematic.sections()
        .sumOf { section ->
            partNumbersInSecondLineOf(section).sumOf { it.value }
        }

    // PART 2

    fun numbersIn(section: List<String>): List<SchematicNumber> {
        return section.flatMap { line -> numbersOn(line) }
    }

    fun gearsIn(section: List<String>): List<Gear> {
        val starIndices = section[1].withIndex()
            .filter { it.value.isGearSymbol() }
            .map { it.index }

        val numbersInSection = numbersIn(section)
            .also { it.println() }

        return starIndices.fold(mutableListOf<Gear>()) { gears, i ->
            val adjacentNumbers = numbersInSection.filter { it.isNear(i) }
            if (adjacentNumbers.size == 2) {
                gears.add(Gear(adjacentNumbers.map { it.value }.reduce { acc, n -> acc * n }))
            }
            gears
        }
    }

    fun part2(schematic: List<String>) = schematic.sections()
        .sumOf { section ->
            gearsIn(section).sumOf { it.ratio }
        }

    // Util
    fun bordered(raw: List<String>): List<String> {
        val border = ".".repeat(raw[0].length)
        return listOf(border) + raw + border
    }

    // Tests

    val testSmallSchematic1 = """
        467..114..
        ...*......
        ..35..633.
    """.trimIndent().lines()
    check(part1(bordered(testSmallSchematic1)) == 467 + 35)
    check(part2(testSmallSchematic1) == 16345)

    val testSmallSchematic2 = """
        ......755.
        ...${'$'}.*....
        .664.598..
    """.trimIndent().lines()

    check(part2(testSmallSchematic2) == 451490)

    val testSchematic = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...${'$'}.*....
        .664.598..
    """.trimIndent().lines()

    check(part2(testSchematic) == 467_835)

    // Day 3 Solution
    val schematic = bordered(readInput("Day03"))
    check(part1(schematic) == 528_799)
    check(part2(schematic) == 84_907_174)
}
