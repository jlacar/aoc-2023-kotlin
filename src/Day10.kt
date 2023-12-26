class Day10() : AoCSolution() {
    override val description = "Day 10: Pipe Maze"

    override fun part1(): Int = -1

    override fun part2(): Int = -1

    companion object {
        fun using(input: List<String>) = Day10()
    }
}

enum class Direction {
    NONE, NORTH, SOUTH, EAST, WEST;
}

enum class PipeType(val symbol: Char) {
    NS('|') {
        override fun connections() = listOf(Direction.NORTH, Direction.SOUTH)
    },
    EW('-') {
        override fun connections() = listOf(Direction.EAST, Direction.WEST)
    },
    NE('L') {
        override fun connections() = listOf(Direction.NORTH, Direction.EAST)
    },
    NW('J') {
        override fun connections() = listOf(Direction.NORTH, Direction.WEST)
    },
    SW('7') {
        override fun connections() = listOf(Direction.SOUTH, Direction.WEST)
    },
    SE('F') {
        override fun connections() = listOf(Direction.SOUTH, Direction.EAST)
    },
    GROUND('.') {
        override fun connections() = emptyList<Direction>()
    },
    START('S') {
        override fun connections() = emptyList<Direction>()
    };

    abstract fun connections(): List<Direction>
}

fun main() {
    val doneWithTDD = false     // TODO toggle this as needed

    val sampleInput1 =
        """
        .....
        .S-7.
        .|.|.
        .L-J.
        .....
        """.trimIndent().lines()

    SolutionChecker(Day10.using(sampleInput1), "sampleInput1").apply {
        checkAnswerForPartOneIs(4)
        checkAnswerForPartTwoIs(-1)
    }

    // TODO temporary breakpoint to aid testing; edit and move around as needed
    check(doneWithTDD) {
        lazyMessage("\n^^^^^^^^^ IGNORE ^^^^^^^^^\nTests PASSED!",
            "RED",
            "GREEN",
            "Now REFACTOR!"
        )
    }

    val sampleInput2 =
        """
        ..F7.
        .FJ|.
        SJ.L7
        |F--J
        LJ...
        """.trimIndent().lines()

    SolutionChecker(Day10.using(sampleInput2), "sampleInput2").apply {
        checkAnswerForPartOneIs(-1)  // 8
        checkAnswerForPartTwoIs(-1)
    }

    "SOLUTION".println()

    SolutionChecker(Day10.using(readInput("DayXX")), "Google").apply {
        checkAnswerForPartOneIs(-1)
        checkAnswerForPartTwoIs(-1)
    }

    SolutionChecker(Day10.using(readInput("DayXXgh")), "GitHub").apply {
        checkAnswerForPartOneIs(-1)
        checkAnswerForPartTwoIs(-1)
    }

    "That's it!".println()
}