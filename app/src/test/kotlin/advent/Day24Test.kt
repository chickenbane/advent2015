package advent

import org.junit.Assert
import org.junit.Test

/**
 * Created by joey on 3/5/17.
 */
class Day24Test {

    @Test
    fun example() {
        val exampleNums = listOf(11, 9, 10, 8, 2, 7, 5, 4, 3, 1)
        val third = exampleNums.sum() / 3
        val g = Day24.knapsack(exampleNums, 0, third, 1, 0)
        if (g == null) {
            Assert.fail("returned null")
        } else {
            Assert.assertEquals("two items", 2, g.size)
            Assert.assertEquals("qe=99", 99, g.qe)
        }
    }

    @Test
    fun answer() {
        val qe = Day24.answer()
        Assert.assertEquals("my answer", 11846773891, qe)
    }

    @Test
    fun answer2() {
        val qe = Day24.answer2()
        Assert.assertEquals("my answer", 80393059, qe)
    }
}