package GameOfLife.tests;

import GameOfLife.Coord;
import GameOfLife.GameOfLifeJava;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameOfLifeJavaTest {
  @Test
  void TestFirstGenInit() {
    int[][] exp = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };
    Coord[] liveCells = {new Coord(2, 4), new Coord(3, 4), new Coord(3, 5)};
    GameOfLifeJava gol = new GameOfLifeJava(4, 8, liveCells);
    int[][] zeroGen = gol.get_currentGeneration();
    assert (Arrays.deepEquals(exp,zeroGen));
  }

  @Test
  void TestSampleFromDocumentation() {
    int[][] exp = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };
    Coord[] liveCells = {new Coord(2, 4), new Coord(3, 4), new Coord(3, 5)};
    GameOfLifeJava gol = new GameOfLifeJava (4, 8, liveCells);
    int[][] fGen = gol.calcNextGen();
    //System.out.println(Arrays.deepToString(gol.get_currentGeneration()));
    assert (Arrays.deepEquals(exp,fGen));
    assertEquals(1, gol.get_currentGenerationNumber(), "Generation should increase after each run");
  }

  @Test
  void TestLiveCellWithLessThanTwoNeighborsDies() {
    Coord[] liveCells = {new Coord(2, 2), new Coord(2, 7), new Coord(3, 6)};
    GameOfLifeJava gol = new GameOfLifeJava (4, 8, liveCells);
    int[][] nGen = gol.calcNextGen();
    int e0Nbrs = nGen[1][1];
    int e1Nbrs = nGen[1][6];
    assertEquals(0, e0Nbrs, "Live Element with no live neighbors should die");
    assertEquals(0, e1Nbrs, "Live Element with one live neighbors should die");
  }

  @Test
  void TestLiveCellWithMoreThanThreeNeighborsDies() {
    Coord[] liveCells = { new Coord(1, 5), new  Coord(2, 3), new Coord(2, 4),
            new Coord(2, 5), new Coord(3, 3), new Coord(3, 4)};
    GameOfLifeJava gol = new GameOfLifeJava (4, 8, liveCells);
    int[][] nGen = gol.calcNextGen();
    int e4Nbrs = nGen[2][3];
    int e5Nbrs = nGen[1][3];
    assertEquals(0, e4Nbrs, "Live Element with four neighbors should die");
    assertEquals(0, e5Nbrs, "Live Element with five live neighbors should die");
  }

  @Test
  void TestLiveCellWithTwoLiveNeighborsStaysAlive() {
    Coord[] liveCells = {new Coord(2, 1), new Coord(3, 1), new Coord(3, 2)};
    GameOfLifeJava gol = new GameOfLifeJava(4, 8, liveCells);
    int[][] nGen = gol.calcNextGen();
    int e2Nbrs = nGen[2][0];
    assertEquals(1, e2Nbrs, "Live Element with exactly two neighbors should stay live");
  }

  @Test
  void TestDeadCellWithtwoThreeNeighborsBecomesAlive() {
    Coord[] liveCells = {new Coord(1, 2), new Coord(2, 1), new Coord(2, 2)};
    GameOfLifeJava gol = new GameOfLifeJava(6, 6, liveCells);
    int[][] nGen = gol.calcNextGen();
    int e3Nbrs = nGen[0][0];
    assertEquals(1, e3Nbrs, "Dead Element should become alive");
  }

  @Test
  void TestDeadCellWithtwoMoreThanThreeNeighborsStaysDead() {
    Coord[] liveCells = {new Coord(1, 1), new Coord(1, 3),
            new Coord(2, 1), new Coord(2, 2), new Coord(2, 3)};
    GameOfLifeJava gol = new GameOfLifeJava(6, 6, liveCells);
    int[][] nGen = gol.calcNextGen();
    int e5Nbrs = nGen[0][1];
    assertEquals(0, e5Nbrs, "Dead Element with 5 neighbors should stay dead");
  }
}