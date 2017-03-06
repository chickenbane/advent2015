package advent

/**
 * Created by joey on 3/5/17.
 */
object Day24 {
    private val copypasta = """
--- Day 24: It Hangs in the Balance ---

It's Christmas Eve, and Santa is loading up the sleigh for this year's deliveries.
However, there's one small problem: he can't get the sleigh to balance. If it isn't balanced, he can't defy physics, and nobody gets presents this year.

No pressure.

Santa has provided you a list of the weights of every package he needs to fit on the sleigh.
The packages need to be split into three groups of exactly the same weight, and every package has to fit.
The first group goes in the passenger compartment of the sleigh, and the second and third go in containers on either side.
Only when all three groups weigh exactly the same amount will the sleigh be able to fly. Defying physics has rules, you know!

Of course, that's not the only problem. The first group - the one going in the passenger compartment -
needs as few packages as possible so that Santa has some legroom left over.
It doesn't matter how many packages are in either of the other two groups, so long as all of the groups weigh the same.

Furthermore, Santa tells you, if there are multiple ways to arrange the packages such that the fewest
possible are in the first group, you need to choose the way where the first group has the smallest
quantum entanglement to reduce the chance of any "complications".
The quantum entanglement of a group of packages is the product of their weights, that is,
the value you get when you multiply their weights together. Only consider quantum entanglement
if the first group has the fewest possible number of packages in it and all groups weigh the same amount.

For example, suppose you have ten packages with weights 1 through 5 and 7 through 11.
For this situation, some of the unique first groups, their quantum entanglements, and a way to divide the remaining packages are as follows:

Group 1;             Group 2; Group 3
11 9       (QE= 99); 10 8 2;  7 5 4 3 1
10 9 1     (QE= 90); 11 7 2;  8 5 4 3
10 8 2     (QE=160); 11 9;    7 5 4 3 1
10 7 3     (QE=210); 11 9;    8 5 4 2 1
10 5 4 1   (QE=200); 11 9;    8 7 3 2
10 5 3 2   (QE=300); 11 9;    8 7 4 1
10 4 3 2 1 (QE=240); 11 9;    8 7 5
9 8 3      (QE=216); 11 7 2;  10 5 4 1
9 7 4      (QE=252); 11 8 1;  10 5 3 2
9 5 4 2    (QE=360); 11 8 1;  10 7 3
8 7 5      (QE=280); 11 9;    10 4 3 2 1
8 5 4 3    (QE=480); 11 9;    10 7 2 1
7 5 4 3 1  (QE=420); 11 9;    10 8 2
Of these, although 10 9 1 has the smallest quantum entanglement (90),
the configuration with only two packages, 11 9, in the passenger compartment gives Santa the most legroom and wins.
In this situation, the quantum entanglement for the ideal configuration is therefore 99.
Had there been two configurations with only two packages in the first group, the one with the smaller quantum entanglement would be chosen.

What is the quantum entanglement of the first group of packages in the ideal configuration?
"""
    private val steps = """
1. find "knapsack" target = (sum of all items) / 3
2. find all knapsack possibilities for target
3. find all combos of groups of three
4. find qe
"""

    val puzzleInput: List<Int> by lazy { FileUtil.resourceToList("input24.txt").map(String::toInt) }

    fun answer(): Long {
        val third = puzzleInput.sum() / 3
        val g = knapsack(puzzleInput, 0, third, 1, 0)
        return g?.qe ?: throw RuntimeException("returned null")
    }

    fun answer2(): Long {
        val fourth = puzzleInput.sum() / 4
        val g = knapsack(puzzleInput, 0, fourth, 1, 0)
        return g?.qe ?: throw RuntimeException("returned null")
    }

    data class Group(val qe: Long, val size: Int)

    fun knapsack(weights: List<Int>, pos: Int, remaining: Int, currQe: Long, currCt: Int): Group? {
        if (remaining == 0) {
            return Group(currQe, currCt)
        } else if (remaining < 0 || pos == weights.size) {
            return null
        }
        val included = knapsack(weights, pos + 1, remaining - weights[pos], currQe * weights[pos], currCt + 1)
        val notIncluded = knapsack(weights, pos + 1, remaining, currQe, currCt)
        if (included == null) return notIncluded
        if (notIncluded == null) return included
        if (included.size < notIncluded.size) return included
        if (notIncluded.size < included.size) return notIncluded

        if (included.qe < notIncluded.qe) return included
        else return notIncluded
    }

    // cheats because it doesn't check to see if remaining weights make valid groups
//
//    data class Sack(val qe: Long, val size: Int, val weight: Int)
//
//    fun knapsack2(weights: List<Int>, targetWeight: Int, idx: Int, curr: Sack): Sack {
//        if (curr.weight == targetWeight) return curr
//        if (idx == weights.size)
//    }

}