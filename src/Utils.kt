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
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun String.asListOfInt(vararg delimiters: String): List<Int> =
    split(*delimiters).filter { it.isNotBlank() }.map { it.toInt() }

fun String.asSetOfInt(vararg delimiters: String): Set<Int> =
    asListOfInt(*delimiters).toSet()

fun String.asListOfLong(vararg delimiters: String): List<Long> =
    split(*delimiters).filter { it.isNotBlank() }.map { it.toLong() }

fun String.asSetOfLong(vararg delimiters: String): Set<Long> =
    asListOfLong(*delimiters).toSet()