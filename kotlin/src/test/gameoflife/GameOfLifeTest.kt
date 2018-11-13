import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GameOfLifeTest {
    @Test
    fun TestFirstGenInit(){
        val exp :Array<Array<Int>> = arrayOf(
                arrayOf(0,0,0,0,0,0,0,0),
                arrayOf(0,0,0,1,0,0,0,0),
                arrayOf(0,0,0,1,1,0,0,0),
                arrayOf(0,0,0,0,0,0,0,0)
        )
        val gol = GameOfLife(4,8, arrayOf(Coord(2,4), Coord(3,4), Coord(3,5)))
        val fGen = gol.currentGeneration
        assert(exp contentDeepEquals fGen)
    }

    @Test
    fun TestSampleFromDocumentation() {
        val exp :Array<Array<Int>> = arrayOf(
                arrayOf(0,0,0,0,0,0,0,0),
                arrayOf(0,0,0,1,1,0,0,0),
                arrayOf(0,0,0,1,1,0,0,0),
                arrayOf(0,0,0,0,0,0,0,0)
        )
        val gol = GameOfLife(4,8, arrayOf(Coord(2,4), Coord(3,4), Coord(3,5)))
        val nGen = gol.calcNextGen()
        assert(exp contentDeepEquals nGen)
        assertEquals(1, gol.generation, "Generation should increase after each run")
    }

    @Test
    fun TestLiveCellWithLessThanTwoNeighborsDies() {
        val gol = GameOfLife(4,8, arrayOf(Coord(2,2), Coord(2,7), Coord(3,6)))
        val nGen = gol.calcNextGen()
        val e0Nbrs = nGen[1][1]
        val e1Nbrs = nGen[1][6]
        assertEquals(0, e0Nbrs, "Live Element with no live neighbors should die")
        assertEquals(0, e1Nbrs, "Live Element with one live neighbors should die")
    }

    @Test
    fun TestLiveCellWithMoreThanThreeNeighborsDies() {
        val gol = GameOfLife(4,8, arrayOf(Coord(1,5),
                Coord(2,3), Coord(2,4), Coord(2,5),
                Coord(3,3), Coord(3,4)))
        val zeroGen = gol.currentGeneration
        val nGen = gol.calcNextGen()
        val e4Nbrs = nGen[2][3]
        val e5Nbrs = nGen[1][3]
        assertEquals(0, e4Nbrs, "Live Element with four neighbors should die")
        assertEquals(0, e5Nbrs, "Live Element with five live neighbors should die")
    }

    @Test
    fun TestLiveCellWithtwoLiveNeighborsStaysAlive() {
        val gol = GameOfLife(4,8, arrayOf(Coord(2,1), Coord(3,1), Coord(3,2)))
        val nGen = gol.calcNextGen()
        val e2Nbrs = nGen[2][0]
        assertEquals(1, e2Nbrs, "Live Element with exactly two neighbors should stay live")
    }

    @Test
    fun TestDeadCellWithtwoThreeNeighborsBecomesAlive() {
        val gol = GameOfLife(6,6, arrayOf(Coord(1,2), Coord(2,1), Coord(2,2)))
        val nGen = gol.calcNextGen()
        val e3Nbrs = nGen[0][0]
        assertEquals(1, e3Nbrs, "Dead Element should become alive")
    }

    @Test
    fun TestDeadCellWithtwoMoreThanThreeNeighborsStaysDead() {
        val gol = GameOfLife(6,6, arrayOf(Coord(1,1), Coord(1,3),
                Coord(2,1), Coord(2,2),Coord(2,3)))
        val nGen = gol.calcNextGen()
        val e5Nbrs = nGen[0][1]
        assertEquals(0, e5Nbrs, "Dead Element with 5 neighbors should stay dead")
    }
}