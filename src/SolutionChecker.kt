class SolutionChecker(val solution: AoCSolution, private val description: String) {
    fun checkAnswerForPartOneIs(expected: Any) {
        val desc = "${solution.description}, Part 1 ($description)"
        assertEqual(desc, expected) { solution -> solution.part1().also { "$desc --> $it".println() } }
    }

    fun checkAnswerForPartTwoIs(expected: Any) {
        val desc = "${solution.description}, Part 2 ($description)"
        assertEqual(desc, expected) { solution -> solution.part2().also { "$desc --> $it".println() } }
    }

    private fun assertEqual(description: String, expected: Any, action: (AoCSolution) -> Any) {
        val actual = action(solution)
        check(expected == actual) {
            lazyMessage(description, expected, actual)
        }
    }
}

interface Describable {
    val description: String
}

abstract class AoCSolution : Describable {
    abstract fun part1(): Any
    abstract fun part2(): Any
}