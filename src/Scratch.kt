fun main() {
    fun firstDigit(w: String): Int {
        return "${w.first { it.isDigit() }}${w.last {it.isDigit()}}".toInt()
    }

    println("123456789".toList())
}