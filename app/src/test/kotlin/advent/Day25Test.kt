package advent

import org.junit.Assert
import org.junit.Test

/**
 * Created by joey on 3/5/17.
 */
class Day25Test {

    @Test
    fun next() {
        val n2 = Day25.next(Day25.first)
        Assert.assertEquals("2nd code", 31916031, n2)
        val n3 = Day25.next(31916031)
        Assert.assertEquals("3rd code", 18749137, n3)
    }

    @Test
    fun rowCol() {
        Assert.assertEquals("0 -> 1,1", Day25.RowCol(1, 1), Day25.rowCol(0))
        Assert.assertEquals("1 -> 2,1", Day25.RowCol(2, 1), Day25.rowCol(1))
        Assert.assertEquals("2 -> 1,2", Day25.RowCol(1, 2), Day25.rowCol(2))
        Assert.assertEquals("3 -> 3,1", Day25.RowCol(3, 1), Day25.rowCol(3))
    }

    @Test
    fun answer() {
        val ans = Day25.answer()
        Assert.assertEquals("my answer", 19980801, ans)
    }
}