package GameOfLife;

import java.util.Arrays;

public class GameOfLifeJava {
    int _curGenNumber;
    int[][] _gen;

    public GameOfLifeJava(int rows, int cols, Coord[] liveCells){
        _curGenNumber = 0;
        int[][] zeroGen = new int[rows][];
        for (int r=0; r<rows; r++) {
            int[] col = new int[cols];
            for(int c=0; c<cols; c++){
                col[c]=0;
            }
            zeroGen[r] = col;
        }

        for (Coord liveCell : liveCells) {
            zeroGen[liveCell.get_row()-1][liveCell.get_col()-1]=1;
        }
        _gen = zeroGen;
    }

    public int get_currentGenerationNumber() {
        return _curGenNumber;
    }

    public int[][] get_currentGeneration(){
        return _gen;
    }

    public int[][] calcNextGen(){
        int[][] nextGen = new int[_gen.length][];
        for (int r=0; r< _gen.length; r++) {
            int[] col = new int[_gen[r].length];
            for (int c=0;  c<col.length; c++ ){
                col[c] = calcNgCell(r,c);
            }
            nextGen[r]=col;
        }

        _curGenNumber++;
        _gen = nextGen;
        return deepCloneGrid(nextGen);
    }

    private int calcNgCell(int row, int col){
        boolean isAlive = _gen[row][col] != 0;
        int liveNeighbors = calcLiveNeighbors(row, col);
        if (isAlive && (liveNeighbors==2 || liveNeighbors==3))
            return 1;
        if (!isAlive && liveNeighbors==3)
            return 1;
        return 0;
    }

    private int calcLiveNeighbors(int row, int col){
        int count = 0;
        for (int r=row-1; r<row+2; r++){
            for (int c=col-1; c<col+2; c++) {
                if (r>=0 && r<_gen.length && c>=0 && c<_gen[r].length){
                    if (!(r==row && c==col) && _gen[r][c]!=0){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private int[][] deepCloneGrid(int[][] org){
        int[][] clone = new int[org.length][];
        for (int r=0; r<org.length; r++){
            int[] col = new int[org[r].length];
            for (int c=0; c<org[r].length; c++){
                col[c] = org[r][c];
            }
            clone[r]=col;
        }
        return clone;
    }
}

