class Coord(val row: Int, val col: Int)

class GameOfLife(val rows:Int, val columns:Int, private val liveCells:Array<Coord>) {
    private var grid :Array<Array<Int>>

    val currentGeneration :Array<Array<Int>>
        get() = deepCloneGrid()

    var generation: Int
        private set

    init {
        generation=0
        grid = Array(rows, {_-> Array(columns,{ _->0})})    //Initialized zero grid
        liveCells.forEach { grid[it.row-1][it.col-1] = 1 }  //Coord are 1 based, array is 0 based
    }

    fun calcNextGen() :Array<Array<Int>>{
        val prevGen = deepCloneGrid()
        for (r in grid.indices) {
            for (c in grid[r].indices){
                grid[r][c] = calcNgCell(r,c,prevGen)
            }
        }
        generation++
        return deepCloneGrid()
    }

    private fun calcNgCell(row :Int, col: Int, prevGen :Array<Array<Int>>) :Int {
        val prevCell = prevGen[row][col]
        val liveNbrs = getLiveNeighbors(row,col, prevGen)
        return when {
            (prevCell !=0 && (liveNbrs==2 || liveNbrs==3)) -> 1  //cell was alive with2 or 3 live neighbors - stay alive
            (prevCell ==0 && liveNbrs==3) -> 1 //Cell was dead but has 3 live neighbors - becomes alive
            else -> 0   //drop dead
        }
    }

    private fun getLiveNeighbors(row: Int, col: Int, prevGen: Array<Array<Int>>): Int {
        var lvNbrs = 0
        val r0 = Math.max(0, row-1)     //prev row cannot be below 0
        val r1 = Math.min(rows-1, row+1)    //next row cannot be behind last row
        val c0 = Math.max(0, col-1) //prev col cannot be below 0
        val c1 = Math.min(columns-1, col+1)
        for (r in r0..r1){
            for (c in c0..c1) {
                if (c==col && r==row) { //we do not want to include the cell itself, only the neighbors
                } else {
                    if (prevGen[r][c]!=0){
                        lvNbrs++
                    }
                }
            }
        }
        return lvNbrs
    }

    fun deepCloneGrid() : Array<Array<Int>> {
        val res = Array(rows, {_-> Array(columns,{ _->0})})    //Initialized zero grid
        for (r in res.indices) {
            for (c in res[r].indices){
                res[r][c] = grid[r][c]
            }
        }
        return res
    }
}