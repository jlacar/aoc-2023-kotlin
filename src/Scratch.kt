
fun main() {
    with (gcd(48, 18)) {
        check(this == 6) { lazyMessage("GCD", 6, this) }
    }

    with (lcm(5, 3)) {
        check(this == 15L)
    }
}