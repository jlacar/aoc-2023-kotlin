data class SchematicNumber(val value: Int, val neighborIndices: Set<Int>) {
    fun isNearAny(symbolIndices: List<Int>): Boolean {
        return (symbolIndices intersect neighborIndices).isNotEmpty();
    }

    companion object {
        fun on(line: String, indices: List<Int>): SchematicNumber {
            val start = indices.first()
            val end = indices.last() + 1
            val value = line.substring(start, end).toInt()
            return SchematicNumber(value, listOf(start-1) union indices union listOf(end))
        }
    }
}

fun main() {

    // DSL Extensions
    fun Char.isSymbol() = this != '.' && !isDigit()

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

    fun partNumbersIn(section: List<String>) : List<SchematicNumber> {
        val symbolIndices = symbolIndices(section)
        val line = section[1]
        return digitIndicesOn(line).fold(mutableListOf<SchematicNumber>()) { numbers, indices ->
            numbers.add(SchematicNumber.on(line, indices))
            numbers
        }.filter {
            it.isNearAny(symbolIndices)
        }
    }

    fun part1(schematic: List<String>) = schematic.windowed(3)
        .sumOf { section ->
            partNumbersIn(section).sumOf { it.value }
        }

    // PART 2

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Util

    fun bordered(raw: List<String>): List<String> {
        val border = ".".repeat(raw[0].length)
        return listOf(border) + raw + border
    }

    // Tests

    val testInput = """
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

    check(part1(bordered(testInput)) == 4361)

    // Day 3 Solution
    val schematic = bordered(readInput("Day03"))
    check(part1(schematic) == 528_799)
//    println(part2(input))
}
