# Advent of Code: Day 7 - Camel Cards (Part 1)



If you're wondering where Days 1 through 6 are, don't worry, they'll be coming out later. Think of this as kind of like how [_Star Wars: Episode IV - A New Hope_](https://en.wikipedia.org/wiki/Star_Wars_(film)) was the first of the series to come out. Hopefully, this initial installment captures your imagination and leaves you wanting more, just like the first Star Wars movie did back in 1977. (Almost 50 years ago!)

Also, I'll try my best to not make any prequels with JarJar Binks-ies or similar kinds of goofiness.

## Advent of Code (AoC)

I have participated in [Advent of Code](https://adventofcode.com) for a few years now. If you've never heard of it, follow the link and check it out. 

I've never joined a leaderboard to compete but it's still a fun thing to do. If you enjoy coding challenges and like pushing the limits of your problem-solving skills, AoC provides ample opportunities for shots of [feel-good hormones](https://www.health.harvard.edu/mind-and-mood/feel-good-hormones-how-they-affect-your-mind-mood-and-body). It's also a great way to discover new ways of thinking about and tackling problems. I do it for the numerous ways  it gives me to practice Test-Driven Development (TDD) and hone my refactoring skills.

Whatever the reason may be that you'd choose to participate, the most important thing is to have fun doing it. We learn best when we're having fun. I have yet to complete all twenty-five puzzles for any of the years I've done AoC but who knows, maybe if I keep practicing I'll eventually earn all fifty stars someday. I might even join a leaderboard and compete. Maybe next year...

## Kotlin

I've been solving the AoC puzzles with [Kotlin](https://kotlinlang.org) first as a way to learn Kotlin and later as a way to refresh and deepen my understanding of the language. I'm [basically a Java guy](https://coderanch.com/wiki/659748/ActiveStaff#junilulacar) but I find Kotlin such a wonderful language to code in, far more enjoyable than Java. Call me a Kotlin fanboy, I don't mind. 

The richness of the standard Kotlin library never ceases to surprise and delight me. It's almost like they ([the people at JetBrains](https://jetbrains.com)) built the language and its library specifically for solving AoC puzzles. Kotlin seems to have every convenient little tool I could ever think of use with the AoC puzzles, and then some. On a good day, I might even solve the puzzle quickly. But the best day for me is when I discover an elegant way to do it in Kotlin. Take the Day 7 puzzle, for example.

## Camel Cards - Part 1

The Day 7 puzzle is "Camel Cards," which is like poker but with a few twists. As with all AoC puzzles, it comes in two parts. You can find the Day 7, Part 1 description here: https://adventofcode.com/2023/day/7. For the sake of brevity, I won't repeat it, so please follow the link and read what it's about so you'll have the necessary context to understand the rest of this article.

### Getting Started

The first task in solving any AoC puzzle is [parsing the input](https://yet-to-be-written-article). Each line of the Day 7 input represents **one play** of Camel Cards. Each play consists of a **hand** of five cards and a **bid**, which factors into how you much you'll win for the hand. All plays in Camel Cards are winners; the only question being how big of a winner is it. This is determined by the hand's rank relative to all other hands in the input data.

Before diving into the details of my Kotlin solution, let's go over a few things about my thought process and the general approach I take when solving an AoC puzzle.

### Start with the code you wish you could write

My approach comes from years of practicing Test-Driven Development (TDD). One of the heuristics I've learned from TDD is to start by writing the code you wish you or someone else could write. When doing regular TDD, I'd start with a test to rough out my ideas. With AoC, I take a slightly different tack, but still with a test-driven mindset informing my decisions along the way.

I use [James Grenning's ZOMBIES](https://blog.wingman-sw.com/tdd-guided-by-zombies), starting with very simple scenarios, half of the 'S' in the mnemonic. Starting with simple scenarios helps you come up with the other half of the 'S': simple solutions. In other words, start with "[Do The Simplest Thing That Could Possibly Work](https://wiki.c2.com/?DoTheSimplestThingThatCouldPossiblyWork)" from XP, or DTSTTCPW for short. As acronyms go, I find that one ironically long but still useful to keep in mind.

### Using Kotlin multiline strings for simple test data

With simplicity and test-first in mind, I chose to embed the examples as plain old multiline string literals. This is the test code I started to write:

```kotlin
// from the Day 7 example
val exampleInput: List<String> =
    """
    32T3K 765
    T55J5 684
    KK677 28
    KTJJT 220
    QQQJA 483
    """.trimIndent().lines()
```
As far as I can tell from the Kotlin standard library commit history on GitHub, Kotlin has always supported [multiline string literals](https://kotlinlang.org/docs/java-to-kotlin-idioms-strings.html#use-multiline-strings), since at least 2015. Java didn't officially support [text blocks in triple quotes](https://docs.oracle.com/en/java/javase/21/text-blocks/index.html) until 2020 with the Java 15 release.

### Companion objects and Factory Methods can do heavy lifting 

After trying various approaches in solving previous days' puzzles, I've settled on a useful pattern: using a [companion object](https://kotlinlang.org/docs/object-declarations.html#companion-objects) to do the heavy lifting of parsing and serving as an [Object Factory](https://yet-to-be-written-article) for [solution objects](https://yet-to-be-written-article).

Starting with code I wanted to write again, I created my first test based on the example given in the problem:

```kotlin
SolutionChecker(Day07.using(exampleInput), "example input").apply {
    checkAnswerForPartOneIs(6440)
}
```
Let's walk through what these few lines of code do:

1. Creates a `Day07` [solution object](https://yet-to-be-written-article) by calling the `using()` factory method, passing in the input data as a list of strings.

2. Passes the `Day07` object as a [constructor argument](https://kotlinlang.org/docs/classes.html#constructors) to `SolutionChecker`, along with a description of this particular solution. The description is used when displaying test results and says something about the input data being used. This solution object uses the example data.

3. Uses the `apply()` [scope function](https://kotlinlang.org/docs/scope-functions.html) to create a small block from which we can call `SolutionChecker` functions like `checkAnswerForPartOneIs()`. We pass in the value we expect to get from `part1()` of the solution. There's also `SolutionChecker.checkAnswerForPartTwoIs()` which we'll get to when we tackle Part 2 of the puzzle.

That's a lot of functionality packed into a few lines of Kotlin, isn't it? Some things to note here:

1. The `SolutionChecker` class is my own invention. It came out of a need to [reduce bloat](https://yet-to-be-written-article) in my solution code.

2. I wrote that code first, or at least something like it, before I wrote its implementation. Writing the code I want to be able to write first gives me a mental model that guides my design decisions.

### Design with the end in mind

Design for me is like taking a road trip. You don't just start driving in whatever direction thinking you'll get there eventually, wherever "there" may be. Neither road trips nor program design work like that. You need to be clear on what your final destination is. Having the end code in mind tells me what main ideas need to be represented and organized in the code. The end vision guides the general direction that design decisions need to be directed.

The road to the `SolutionChecker` class abstraction was relatively long: I didn't come up with that abstraction until Day 9, when I felt enough pain from the code bloat to want to do something about it. But that's a story for another installment. What you're seeing here is the result of refactoring my original and bloated Day 7 solution to something much cleaner.

### Making code readable and understandable reduces costs

I hope you think the refactored code is readable and easy to understand. Writing readable and understandable code is a habit that is deeply ingrained in me after years of working with legacy code. I'd like more developers to be the same way. We can never have too many developers who write code with readability and understandability as a core guiding principle.

Sadly, in my experience, most developers only go as far as to make the code work. Once they think it works, they quickly move on to the next task, often leaving a trail of messy code in their wake.

Sure, writing readable code takes more time and effort up front. If you're only looking at the short-term gain, this might seem like a cost-effective way of developing software. But the perceived cost savings of a "Just Git 'er Done!" attitude, [just like the cake](https://en.wikipedia.org/wiki/The_cake_is_a_lie), is a lie.

### The biggest cost of code is NOT in writing it

Kent Beck, the co-creator of JUnit and the Agile Manifesto, recently published the first of a planned series of books on design. [_Tidy First? A Personal Exercise in Empirical Software Design_](https://learning.oreilly.com/library/view/tidy-first/9781098151232/) is an interesting title and a great read. I encourage every developer to check it out and really take the lessons in it to heart. There are many good and important lessons there that we can all apply in our daily work, all the time.

In Chapter 13, he writes about the cost of code.

> "The biggest cost of code is the cost of reading and understanding it, not the cost of writing it." —Kent Beck

If you've worked with code for a while, you've likely experienced the "joy" of looking at something for several minutes and wondering how the heck it ever got this messy and who the heck would write something like that. Then you realize that it was _you_ who wrote it a few months ago, or maybe even more recently.

Or maybe someone has asked you about some code you wrote that they're struggling to understand. After several minutes of head scratching, squinting, and brow furrowing, you have to shrug and sheepishly admit that you can't remember what you were thinking when you wrote it either. Time to fire up that debugger!

In all my years of working with [legacy code](https://yet-to-be-written-article#legacy-code), the biggest impediment and source of frustration and wasted time has invariably been code that's difficult to read and understand. Code without tests is the second worst of this kind. Hard-to-read-and-understand code with hard-to-read-and-understand tests are at the top of the worst list. Go ahead, change my mind.

### Code should tell a story

I often hear it said that code spends more time being read than it does being written. The more we make code easier to read, the less people need to spend time trying to decipher it, and the sooner they can down to the business of maintaining it. In other words, you can help people get busy coding, or keep them busy reading! (Read that again with your best Morgan Freeman narrating "The Shawshank Redemption" voice).

Another Kent Beck-ism is that code should tell a story. I first heard him say this on The Software Engineering Radio podcast, [Episode 167: The History of JUnit and the Future of Testing with Kent Beck](https://www.se-radio.net/2010/09/episode-167-the-history-of-junit-and-the-future-of-testing-with-kent-beck/) from 2010. My experience has shown this to be a basic truth of good programs.

Readable code tells a story and good names are critical to a good story. Good names are meaningful and help provide and create context. Names that are not meaningful detract and distract from the story the code wants to tell.

Meaningful names are the strands of ideas. We spin those strands together to create threads. We weave those threads into the cloth of our program, which we then cut and sew together to form vestments that clothe our understanding of the problem and its solution. 

If we do a good job at clothing our story with relevant ideas, the end result is beautiful and pleasing to the senses, and makes us say "Yes! This is the way!" 

Poor storytelling in code results in something that's not pleasing, difficult to follow, uncomfortable to work with, and leaves us asking "Wait, what? Why... ???!" with perhaps a couple of choice words tacked on to the end, for emphasis.

### The code's story should fit the problem well

Code that reads like a well-written story is like a well-tailored suit that fits its wearer well and makes them easy on the eyes. Code that doesn't is usually difficult, if not morbidly fascinating, to look at.

Here's how I ended up telling the story of Day 7, Part 1, in Kotlin:
```kotlin
class Day07(private val plays: List<CamelCardPlay>) : AoCSolution() {

    override val description = "Day 7: Camel Cards"

    override fun part1(): Int = totalWinnings(plays.sortedWith( compareBy { it.normalStrength } ))

    override fun part2(): Int = -1  // Placeholder only: Solve this next

    private fun totalWinnings(rankedPlays: List<CamelCardPlay>): Int =
        rankedPlays.mapIndexed { rank, play -> (rank + 1) * play.bid }.sum()

    companion object {
        fun using(input: List<String>) = Day07(
            plays = input.map {
                val (hand, bid) = it.split(" ")
                CamelCardPlay(hand, bid.toInt())
            }
        )
    }
}

fun main() {
    // ... test first up here

    "SOLUTION".println()

    SolutionChecker(Day07.using(readInput("Day07")), "my puzzle input").apply {
        checkAnswerForPartOneIs(251_216_224)
    }    
}

```
These are the names I used to make the code tell a story consistent with the problem description:
1. `CamelCards`
2. `plays`
3. `totalWinnings`
3. `normalStrength`
4. `rankedPlays`
5. `hand` and `bid`

These strands were eventually woven into the cloth of the solution:
1. `totalWinnings`, which works on a list of `rankedPlays`
2. `SolutionChecker`, works with an `AoCSolution` which can give the answer to `part1()` of the puzzle

The rest of the story was built on the foundation that the Kotlin language and its standard library provides:
1. Extension functions to create expressive semantics like `plays.sortedWith()` and `comparedBy { it.normalStrength }`
2. Scope functions that help create small, context-containment areas like with `.apply { ... }`
3. Implied context like the call to `checkAnswerForPartOneIs()` which has the implied receive of `SolutionChecker`
4. Companion objects that allow you to write fluent code like `Day07.using(readInput("Day07"))`
5. Constructs like lambdas, `.mapIndexed {}`, and `.sum()` that allow you to write functional-style programs.

This is what the output looks like:

```text
Day 7: Camel Cards, Part 1 (example input) --> 6440
SOLUTION
Day 7: Camel Cards, Part 1 (my puzzle input) --> 251216224
```

All author's and non-author's bias aside, don't you think that's a beauty to behold?

### Under the Hood

If you're wondering what's under the hood of the main solution, here it is:

```kotlin
enum class HandType {
    HIGH_CARD, ONE_PAIR, TWO_PAIRS, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND;

    val strength: Char = 'A' + this.ordinal

    companion object {
        fun of(hand: String): HandType {
            val distinctRanks = hand.charCounts()
            return when (distinctRanks.count { it.value > 0 }) {
                1 -> FIVE_OF_A_KIND
                2 -> if (distinctRanks.any { it.value == 4 }) FOUR_OF_A_KIND else FULL_HOUSE
                3 -> if (distinctRanks.any { it.value == 3 }) THREE_OF_A_KIND else TWO_PAIRS
                4 -> ONE_PAIR
                else -> HIGH_CARD
            }
        }
    }
}

data class CamelCardPlay(val hand: String, val bid: Int) {

    private val countOf = hand.charCounts()
    // ... some part 2 stuff
    
    val normalStrength = strength(HandType.of(hand).strength, hand, normalRules)
    // ... more part 2 stuff
    
    companion object {
        val normalRules = strengthOrder("23456789TJQKA")
        // ... more part 2 stuff
        
        private fun strengthOrder(rankOrder: String): Map<Char, Char> =
            mutableMapOf<Char, Char>().apply {
                rankOrder.zip("ABCDEFGHIJKLM") { ch, strength -> this[ch] = strength }
            }

        fun strength(typeStrength: Char, hand: String, strengthOf: Map<Char, Char>) =
            hand.fold(typeStrength.toString()) { acc, ch -> acc + strengthOf[ch] }
    }
}
```

### The key to this solution: The Simplest Thing That Could Possibly Work

To me, the main thing, the part that took the longest, was figuring out how to assign the strength to each hand played. One complication to this was that it was a relative value and depended on the strength of other hands.

I spent quite some time playing around approaches for determining a hand's strength, like calculating a hash and other math-based strategies. I couldn't get any of them to work reliably enough. That's when DTSTTCPW came to the rescue.

It suddenly dawned on me that I could just use plain old Strings to sort the hands. If I mapped each char to "ABCDEFGHIJKLM", the first thirteen letters of the alphabet, that would be enough. I only needed thirteen letters because that's how many different card values there are to compare.

For the hand type strength, I used the same encoding, even though I'd only use the first seven letters.

So, the hand type gets encoded as `'A' + this.ordinal` which would make `HIGH_CARD` get ranked below everything else and `FIVE_OF_A_KIND` ranked the highest of all the types.

Essentially, an alphabetical encoding was the simplest thing that could possible work.

Here's the `strength()` function again:

```kotlin
fun strength(typeStrength: Char, hand: String, strengthOf: Map<Char, Char>) =
        hand.fold(typeStrength.toString()) { acc, ch -> acc + strengthOf[ch] }
```
That reads as: Starting with the hand type strength, go through all the cards in the hand and add their individual strength to the end. The result gives you the hand's strength.

### An opportunity to tell an even better story

Remember how I said that names are the strands of the ideas we weave into stories? Well, the names `acc` and `ch` don't really resonate with the story we just told, do they? 

How about we try this instead:
```kotlin
typealias StrengthMapping = Map<Char, Char>

data class CamelCardPlay(val hand: String, val bid: Int) {
    ...
    val normalStrength = strength(HandType.of(hand), hand, normalRules)
    
    companion object {
        ...

        fun strength(handType: HandType, hand: String, strengthOf: StrengthMapping) =
            hand.fold(handType.strength.toString()) { strengthSoFar, card ->
                strengthSoFar + strengthOf[card]
            }
    }
}
```
If you check [my GitHub repo history](https://github.com/jlacar/aoc-2023-kotlin/commits/main/src/Day07.kt), you might see that I didn't come up with this refactoring until today (December 21, 2023).

