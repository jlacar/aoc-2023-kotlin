
fun main() {
    with (48 gcd 18) {
        check(this == 6) { lazyMessage("GCD", 6, this) }
    }

    with (5 lcm 3) {
        check(this == 15)
    }
}