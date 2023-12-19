import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * Create a lazyMessage for check failures
 */
fun lazyMessage(description: String, expected: Any?, actual: Any?, extra: Any? = "-"): String =
    """FAILED $description
        |  expected   [$expected]
        |  but got    [$actual]
        |  $extra
    """.trimMargin()

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun productOfAll(values: Iterable<Int>) = values.fold(1) { prod: Int, n: Int -> prod * n }

fun String.asListOfInt(vararg delimiters: String): List<Int> =
    split(*delimiters).filter { it.isNotBlank() }.map { it.toInt() }

fun String.toInts(): List<Int> = this.asListOfInt(" ")

fun String.asSetOfInt(vararg delimiters: String): Set<Int> =
    asListOfInt(*delimiters).toSet()

fun String.asListOfLong(vararg delimiters: String): List<Long> =
    split(*delimiters).filter { it.isNotBlank() }.map { it.toLong() }

fun String.toLongs(): List<Long> = this.asListOfLong(" ")

fun String.charFrequencies(): Map<Char, Int> = mutableMapOf<Char, Int>()
    .let { frequencyOf ->
        forEach { ch -> frequencyOf[ch] = frequencyOf.getOrDefault(ch, 0) + 1 }
        frequencyOf
    }

/** Math stuff */

fun gcd(a: Long, b: Long): Long {
    var n1 = a
    var n2 = b
    while (n2 != 0L) {
        val temp = n2
        n2 = n1 % n2
        n1 = temp
    }
    return n1
}

fun gcd(a: Int, b: Int): Int = gcd(a.toLong(), b.toLong()).toInt()

fun lcm(n1: Long, n2: Long) = n1 * n2 / gcd(n1, n2)

//fun lcm(longs: List<Long>): Long =
//    longs.subList(1, longs.lastIndex).fold(longs.first())
//    {acc: Long, next: Long -> lcm(acc, next) }

//fun lcm(ints: List<Int>): Long = lcm(ints.map(Int::toLong))

