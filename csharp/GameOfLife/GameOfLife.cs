using System;
using System.Linq;

namespace GameOfLifeKata
{
    public struct Coord
    {
        public Coord(int row, int col)
        {
            this.col = col;
            this.row = row;
        }
        public int col;
        public int row;
    }

    public class GameOfLife
    {
        private int _rows;
        private int _cols;
        private int[][] _genGrid;
        private int _genNo;

        public GameOfLife(int rows, int cols, Coord[] liveCells)
        {
            _genNo = 0;
            _rows = rows;
            _cols = cols;
            _genGrid = new int[rows][];

            for (int r=0; r <rows; r++)
            {
                _genGrid[r] = new int[cols];
                for (int c=0; c<cols; c++)
                {
                    _genGrid[r][c] = liveCells.Any(v => (v.row == r + 1) && (v.col == c + 1)) ? 1 : 0;
                }
            }
        }

        public int[][] CurrentGeneration => _genGrid.Select(x => x.ToArray()).ToArray();    //Deep Clone of jagged array

        public int GenerationNumber => _genNo;

        public int[][] NextGen()
        {
            int[][] nGen = new int[_rows][];
            for (int r=0; r< _rows; r++)
            {
                int[] row = new int[_cols];
                for (int c=0; c<_cols; c++)
                {
                    row[c] = CalcNextGenCell(r, c);
                }
                nGen[r] = row;
            }
            _genGrid = nGen;
            _genNo++;
            return nGen;
        }

        private int CalcNextGenCell(int r, int c)
        {
            bool isAlive = _genGrid[r][c] != 0;
            int liveNeighbors = CalcLiveNeighbors(r, c);
            if (isAlive && (liveNeighbors == 2 || liveNeighbors == 3))
                return 1;   //keep alive
            if (!isAlive && liveNeighbors == 3)
                return 1;   //Resurrected from the dead
            return 0;   //Die!
        }

        private int CalcLiveNeighbors(int r, int c)
        {
            var count = 0;
            for (int i = r-1; i<=r+1; i++)
            {
                if (i>=0 && i < _rows)
                {
                    for (int j=c-1; j<=c+1; j++)
                    {
                        if (j>=0 && j < _cols)
                        {
                            if (_genGrid[i][j] == 1 && !(r==i&&c==j))
                                count++;
                        }
                    }
                }
            }
            return count;
        }
    }
}
