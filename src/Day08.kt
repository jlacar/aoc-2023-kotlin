/**
 * --- Day 8: Haunted Wasteland ---
 */

typealias Node = Pair<String, String>
typealias NodePredicate = (String) -> Boolean

class Day08(val instructions: String, val nodes: Map<String, Node>) {

    fun part1(): Int = stepsFrom("AAA") { it == "ZZZ" }

    /* approach adapted from Todd Ginsberg (tginsberg) */
    fun part2(): Long = nodes.keys
        .filter { it.endsWith('A') }
        .map { stepsFrom(it) { it.endsWith('Z') } .toLong() }
        .reduce { prev: Long, next: Long -> prev lcm next }

    // TODO try profiling as inline vs not inline since it takes a function argument
    private fun stepsFrom(start: String, endLabelFound: NodePredicate): Int {
        var steps = 0
        var current = start
        while (!endLabelFound(current)) {
            current = nodes[current]?.pick(instructions[steps++ % instructions.length])
                        ?: error("Map entry with key=[$current] not found!")
        }
        return steps
    }

    private fun Node.pick(side: Char) = if (side == 'L') first else second

    companion object {
        fun using(input: List<String>) = Day08(
            instructions = input.first(),
            nodes = input.drop(2).associate {
                it.substring(0..2) to (it.substring(7..9) to it.substring(12..14))
            }
        )
    }
}

fun main() {

    val sampleInput1 =
        """
        RL
        
        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
        """.trimIndent().lines()

    Day08.using(sampleInput1).apply {
        with (part1()) {
            "Part 1 (example 1) -> $this".println()

            val expected = 2
            check(this == expected) {
                lazyMessage("Part 1 (example 1)", expected, this)
            }
        }
    }

    val sampleInput2 =
        """
        LLR
        
        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)
        """.trimIndent().lines()

    Day08.using(sampleInput2).apply {
        with (part1()) {
            "Part 1 (example 2) -> $this".println()

            val expected = 6
            check(this == expected) {
                lazyMessage("Part 1 (example 2)", expected, this)
            }
        }
    }

    val sampleInputPart2 =
        """
        LR
        
        11A = (11B, XXX)
        11B = (XXX, 11Z)
        11Z = (11B, XXX)
        22A = (22B, XXX)
        22B = (22C, 22C)
        22C = (22Z, 22Z)
        22Z = (22B, 22B)
        XXX = (XXX, XXX)        
        """.trimIndent().lines()

    Day08.using(sampleInputPart2).apply {
        with (part2()) {
            "Part 2 (sample) -> $this".println()

            val expected: Long = 6
            check(this == expected) {
                lazyMessage("Part 2 (example)", expected, this)
            }
        }
    }

    check(true) {
        """
        |
        | All tests PASS! To see the answers:
        | - Set the flag in this gate check to true
        | - Remove or disable .also() debugs calls
        """.trimMargin()
    }

    "SOLUTION".println()

    val myPuzzleInput = readInput("Day08")

    Day08.using(myPuzzleInput).apply {
        with (part1()) {
            "Part 1 -> $this".println()

            val correctAnswer = 19_241
            check(this == correctAnswer) {
                lazyMessage("You broke Part 1!", correctAnswer, this)
            }
        }

        with (part2()) {
            "Part 2 -> $this".println()

            val correctAnswer = 9_606_140_307_013
            check(this == correctAnswer) {
                lazyMessage("You broke Part 2!", correctAnswer, this)
            }
        }
    }

    "That's it!".println()
}