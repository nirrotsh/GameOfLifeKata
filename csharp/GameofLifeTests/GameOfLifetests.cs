using GameOfLifeKata;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace GameofLifeTests
{
    [TestClass]
    public class GameOfLifetests
    {
        [TestMethod]
        public void TestCtor()
        {
            var res = new int[][]{
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0 },
                new int[] { 0, 0, 0, 1, 0, 0, 0, 0 },
                new int[] { 0, 0, 0, 1, 1, 0, 0, 0 },
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0 }};
            var gol = new GameOfLife(4, 8, new Coord[] { new Coord(2, 4), new Coord(3, 4), new Coord(3, 5) });
            var zeroGen = gol.CurrentGeneration;
            Assert.IsTrue(CompareGrids(res, zeroGen), "Zero Grids do not match expected");
            Assert.AreEqual(0, gol.GenerationNumber, "Generation number should be 0(zero) on creation");
        }

        [TestMethod]
        public void TestDocumentationSample()
        {
            var res = new int[][]{
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0 },
                new int[] { 0, 0, 0, 1, 1, 0, 0, 0 },
                new int[] { 0, 0, 0, 1, 1, 0, 0, 0 },
                new int[] { 0, 0, 0, 0, 0, 0, 0, 0 }};
            var gol = new GameOfLife(4, 8, new Coord[] { new Coord(2, 4), new Coord(3, 4), new Coord(3, 5) });
            int[][] nGen = gol.NextGen();
            Assert.IsTrue(CompareGrids(res, nGen), "First Gen Grid do not match expected");
            Assert.AreEqual(1, gol.GenerationNumber, "Generation number should be 0(zero) on creation");
        }

        [TestMethod]
        public void TestLiveCellWithLessOfTwoNeighborsShouldDie()
        {
            var gol = new GameOfLife(4, 8, new Coord[] { new Coord(2, 2), new Coord(2, 7), new Coord(3, 6) });
            int[][] nGen = gol.NextGen();
            Assert.AreEqual(0, nGen[1][1], "Live cell with no neighbors should die");
            Assert.AreEqual(0, nGen[1][6], "Live cell with only one neighbor should die");
        }

        [TestMethod]
        public void TestLiveCellWithMoreThanThreeNeighborsShouldDie()
        {
            var gol = new GameOfLife(4, 8, new Coord[] { new Coord(1, 5),
                new Coord(2, 3), new Coord(2, 4) , new Coord(2, 5),
                new Coord(3,3), new Coord(3,4)});
            int[][] nGen = gol.NextGen();
            Assert.AreEqual(0, nGen[2][3], "Live cell with four neighbors should die");
            Assert.AreEqual(0, nGen[1][3], "Live cell with five neighbors should die");
        }

        [TestMethod]
        public void TestLiveCellWithTwoNeighborsShouldLive()
        {
            var gol = new GameOfLife(4, 8, new Coord[] { new Coord(2, 1),new Coord(3, 1), new Coord(3, 2)});
            int[][] nGen = gol.NextGen();
            Assert.AreEqual(1, nGen[2][0], "Live cell with two neighbors should stay alive");
        }


        [TestMethod]
        public void TestDeadCellWithExactlyThreeNeighborsShouldBecomeAlive()
        {
            var gol = new GameOfLife(6, 6, new Coord[] { new Coord(1, 2), new Coord(2, 1), new Coord(2, 2) });
            int[][] nGen = gol.NextGen();
            Assert.AreEqual(1, nGen[0][0], "Dead cell with exactly three neighbors should become alive");
        }

        [TestMethod]
        public void TestDeadCellWithMoreThanThreeNeighborsShouldStayDead()
        {
            var gol = new GameOfLife(6, 6, new Coord[] { new Coord(1, 1), new Coord(1, 3),
                new Coord(2, 1), new Coord(2, 2), new Coord(2,3) });
            int[][] nGen = gol.NextGen();
            Assert.AreEqual(0, nGen[0][1], "Dead cell with five neighbors should stay dead");
        }
        private bool CompareGrids(int[][] a, int[][] b)
        {
            if (a == null && b == null)
                return true;
            if (a == null || b == null)
                return false;
            if (a.Length != b.Length)
                return false;
            for (int r = 0; r<a.Length; r++)
            {
                int[] r1 = a[r];
                int[] r2 = b[r];
                if (r1.Length != r2.Length)
                    return false;
                for (int c=0; c< r1.Length; c++)
                {
                    if (r1[c] != r2[c])
                        return false;
                }
            }
            return true;
        }
    }
}
