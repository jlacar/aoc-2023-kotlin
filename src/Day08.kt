/**
 * --- Day 8: Haunted Wasteland ---
 */

class Day08(val instructions: String, val nodes: Map<String, Pair<String, String>>) {
    fun part1(): Int {
        val wrapLength = instructions.length
        var lastVisited = "AAA"
        val steps = generateSequence(0, Int::inc).indexOfFirst { i: Int ->
            val nextNode = nodes[lastVisited] ?: error("Node [$lastVisited] not found!")
            val next = nextNode.pick(instructions[i % wrapLength])
            lastVisited = next
            next == "ZZZ"
        }
        return steps + 1
    }

    private fun Pair<String, String>.pick(side: Char) = if (side == 'L') first else second

    fun part2(): Int = 0

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

            val expected = 2
            check(this == expected) {
                lazyMessage("Part 1 (example 1)", expected, this)
            }
        }

//        with (part2()) {
//            "Part 2 (sample) -> $this".println()
//
//            val expected = ?
//            check(this == expected) {
//                lazyMessage("Part 2 (example)", expected, this)
//            }
//        }
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

//        with (part2()) {
//            "Part 2 (sample) -> $this".println()
//
//            val expected = ?
//            check(this == expected) {
//                lazyMessage("Part 2 (example)", expected, this)
//            }
//        }
    }

//    check(false) {"Temp breakpoint"}

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

            val correctAnswer = 19241
            check(this == correctAnswer) {
                lazyMessage("You broke Part 1!", correctAnswer, this)
            }
        }

//        with (part2()) {
//            "Part 2 -> $this".println()
//
//            val correctAnswer = 250825971
//            check(this == correctAnswer) {
//                lazyMessage("You broke Part 2!", correctAnswer, this)
//            }
//        }
    }

    "That's it!".println()
}