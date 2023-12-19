/**
 * --- Day 8: Haunted Wasteland ---
 */

typealias Node = Pair<String, String>

class Day08(val instructions: String, val nodes: Map<String, Node>) {

    fun part1(): Long = stepsFrom("AAA") { label -> label == "ZZZ" }.toLong()

    fun part2(): Long = leastCommonMultiple( nodes
        .filter { it.key.endsWith('A') }
        .map { stepsFrom(it.key) { label -> label.endsWith('Z') } }
    )

    private fun leastCommonMultiple(steps: List<Int>): Long =
        steps.fold(1L) { a: Long, b: Int -> lcm(a, b.toLong()) }

    private fun stepsFrom(start: String, isEndLabel: (String) -> Boolean): Int {
        val lastVisited =  mutableListOf(start)
        return generateSequence(0, Int::inc).indexOfFirst { index: Int ->
            lastVisited.replaceAll { label ->
                val nextNode = nodes[label] ?: error("Node[$label] does not exist!")
                nextNode.pick(instructions[index % instructions.length])
            }
            lastVisited.any(isEndLabel)
        }.inc()
    }

    private fun Node.pick(side: Char) = if (side == 'L') first else second

    companion object {
        fun using(input: List<String>) = Day08(
            instructions = input.first(),
            nodes = mutableMapOf<String, Pair<String, String>>().apply {
                input.drop(2).forEach {
                    val (label, lr) = it.split(" = ")
                    val (left, right) = lr
                        .substringAfter("(").substringBefore(")")
                        .split(", ")
                    this[label] = Pair(left, right)
                }
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

            val expected: Long = 2
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

            val expected: Long = 6
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

            val correctAnswer: Long = 19_241
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