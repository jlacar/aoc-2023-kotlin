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

fun String.charFrequencies(): Map<Char, Int> {
    val charCounts = mutableMapOf<Char, Int>()
    forEach { ch -> charCounts[ch] = charCounts.getOrDefault(ch, 0) + 1 }
    return charCounts
}
