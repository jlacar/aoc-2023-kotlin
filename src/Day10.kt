import MazePipe.*

typealias MazeRow = List<MazeTile>
typealias Maze = List<MazeRow>

class Day10(val maze: Maze) : AoCSolution() {
    override val description = "Day 10: Pipe Maze"

    override fun part1(): Int = -1

    override fun part2(): Int = -1

    companion object {
        fun using(input: List<String>) = Day10(
            maze = input.mapIndexed { row, line ->
                line.mapIndexed { col, symbol ->
                    MazeTile(
                        pipe = MazePipe.of(symbol),
                        location = MazeLocation(row, col)
                    )
                }
            }
        )
    }
}

data class MazeLocation(val row: Int, val col: Int)

data class MazeTile(val pipe: MazePipe, val location: MazeLocation)

enum class Direction {
    NONE, NORTH, SOUTH, EAST, WEST;
}

enum class MazePipe(val symbol: Char) {
    TOP_BOTTOM('|') {
        override fun bottomConnections() = setOf(TOP_BOTTOM, TOP_LEFT, TOP_RIGHT)
        override fun rightConnections() = emptySet<MazePipe>()
    },
    LEFT_RIGHT('-') {
        override fun bottomConnections() = emptySet<MazePipe>()
        override fun rightConnections() = setOf(LEFT_RIGHT, TOP_LEFT, BOTTOM_LEFT)
    },
    TOP_RIGHT('L') {
        override fun bottomConnections() = emptySet<MazePipe>()
        override fun rightConnections() = setOf(LEFT_RIGHT, TOP_LEFT, BOTTOM_LEFT)
    },
    TOP_LEFT('J') {
        override fun bottomConnections() = emptySet<MazePipe>()
        override fun rightConnections() = emptySet<MazePipe>()
    },
    BOTTOM_LEFT('7') {
        override fun bottomConnections() = setOf(TOP_BOTTOM, TOP_LEFT, TOP_RIGHT)
        override fun rightConnections() = emptySet<MazePipe>()
    },
    BOTTOM_RIGHT('F') {
        override fun bottomConnections() = setOf(TOP_BOTTOM, TOP_LEFT, TOP_RIGHT)
        override fun rightConnections() = setOf(LEFT_RIGHT, TOP_LEFT, BOTTOM_LEFT)
    },
    GROUND('.') {
        override fun bottomConnections() = emptySet<MazePipe>()
        override fun rightConnections() = emptySet<MazePipe>()
    },
    START('S') {
        override fun bottomConnections() = emptySet<MazePipe>()
        override fun rightConnections() = emptySet<MazePipe>()
    };

    abstract fun bottomConnections(): Set<MazePipe>
    abstract fun rightConnections(): Set<MazePipe>

    infix fun connectsAtBottomTo(other: MazePipe) = other in bottomConnections()
    infix fun connectsAtRightTo(other: MazePipe) = other in rightConnections()

    companion object {
        fun of(symbol: Char) = entries.firstOrNull { it.symbol == symbol } ?: error("Invalid PipeConnection")
    }
}

fun main() {

    val doneWithTDD = false     // TODO toggle this as needed

    with(TOP_BOTTOM) {
        check(listOf(TOP_BOTTOM, TOP_LEFT, TOP_RIGHT).all { this connectsAtBottomTo it })
        check(entries.toTypedArray().none { this connectsAtRightTo it })
    }

    with(LEFT_RIGHT) {
        check(entries.toTypedArray().none { this connectsAtBottomTo it })
        check(listOf(LEFT_RIGHT, TOP_LEFT, BOTTOM_LEFT).all { this connectsAtRightTo it })
    }

    with(TOP_LEFT) {
        check(entries.toTypedArray().none { this connectsAtBottomTo it })
        check(entries.toTypedArray().none { this connectsAtRightTo it })
    }

    with(TOP_RIGHT) {
        check(entries.toTypedArray().none { this connectsAtBottomTo it })
        check(listOf(LEFT_RIGHT, TOP_LEFT, BOTTOM_LEFT).all { this connectsAtRightTo it })
    }

    with(BOTTOM_LEFT) {
        check(listOf(TOP_BOTTOM, TOP_LEFT, TOP_RIGHT).all { this connectsAtBottomTo it })
        check(entries.toTypedArray().none { this connectsAtRightTo it })
    }

    with(BOTTOM_RIGHT) {
        check(listOf(TOP_BOTTOM, TOP_LEFT, TOP_RIGHT).all { this connectsAtBottomTo it })
        check(listOf(LEFT_RIGHT, TOP_LEFT, BOTTOM_LEFT).all { this connectsAtRightTo it })
    }

    with(GROUND) {
        check(entries.toTypedArray().none { this connectsAtBottomTo it })
        check(entries.toTypedArray().none { this connectsAtRightTo it })
    }

    listOf('|' to TOP_BOTTOM, '-' to LEFT_RIGHT, 'L' to TOP_RIGHT, 'J' to TOP_LEFT,
           'F' to BOTTOM_RIGHT, '7' to BOTTOM_LEFT, '.' to GROUND, 'S' to START)
    .forEach { (symbol, pipe) ->
        check(MazePipe.of(symbol) == pipe)
    }

//
//    val sampleInput1 =
//        """
//        .....
//        .S-7.
//        .|.|.
//        .L-J.
//        .....
//        """.trimIndent().lines()

    val sampleInput1 =
        """
        ..F7.
        .FJ|.
        SJ.L7
        |F--J
        LJ...
        """.trimIndent().lines()

    with(Day10.using(sampleInput1)) {
        this.maze.forEach { row ->
            row.joinToString("") { it.pipe.symbol.toString() }.println()
        }
    }

    // TODO temporary breakpoint to aid testing; edit and move around as needed
    check(doneWithTDD) {
        lazyMessage("\n^^^^^^^^^ IGNORE ^^^^^^^^^\nTests PASSED!",
            "RED",
            "GREEN",
            "Now REFACTOR!"
        )
    }

    SolutionChecker(Day10.using(sampleInput1), "sampleInput1").apply {
        checkAnswerForPartOneIs(4)
        checkAnswerForPartTwoIs(-1)
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