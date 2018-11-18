package GameOfLife;

public class Coord {
    private int _row;
    private int _col;

    public Coord(int row, int col){
        this._row = row;
        this._col = col;
    }

    public int get_row() { return this._row; }
    public int get_col() { return this._col; }
}
