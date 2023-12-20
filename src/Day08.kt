typealias Node = Pair<String, String>
typealias NodePredicate = (String) -> Boolean

class Day08(val instructions: String, val nodes: Map<String, Node>) : AoCSolution() {

    override val description = "Day 8: Haunted Wasteland"

    override fun part1(): Int = stepsFrom("AAA") { it == "ZZZ" }

    /* approach adapted from Todd Ginsberg (tginsberg) */
    override fun part2(): Long = nodes.keys
        .filter { it.endsWith('A') }
        .map { stepsFrom(it) { it.endsWith('Z') }.toLong() }
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
        private val LABEL = 0..2
        private val LEFT = 7..9
        private val RIGHT = 12..14
        fun using(input: List<String>) = Day08(
            instructions = input.first(),
            nodes = input.drop(2).associate {
                it.substring(LABEL) to (it.substring(LEFT) to it.substring(RIGHT))
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

    SolutionChecker(Day08.using(sampleInput1), "example 1").apply {
        checkAnswerForPartOneIs(2)
    }

    val sampleInput2 =
        """
        LLR
        
        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)
        """.trimIndent().lines()

    SolutionChecker(Day08.using(sampleInput2), "example 2").apply {
        checkAnswerForPartOneIs(6)
    }

    val sampleInput3 =
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

    SolutionChecker(Day08.using(sampleInput3), "example 3").apply {
        checkAnswerForPartTwoIs(6L)
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

    SolutionChecker(Day08.using(readInput("Day08")), "Google").apply {
        checkAnswerForPartOneIs(19_241)
        checkAnswerForPartTwoIs(9_606_140_307_013)
    }

    "That's it!".println()
}