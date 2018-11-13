package gameoflife

//import "fmt"

type Coord struct {
	Row, Col uint
}

type Grid [][]uint8

func Init(rows uint, cols uint, liveCells []Coord) Grid {
	res := make([][]uint8, rows)
	for i := uint(0); i < rows; i++ {
		res[i] = make([]uint8, cols)
	}
	for j := 0; j < len(liveCells); j++ {
		lc := liveCells[j]
		res[lc.Row-1][lc.Col-1] = 1
	}
	return res
}

func nextGen(pGen Grid) Grid {
	rows := len(pGen)
	nGen := make([][]uint8, rows)
	for i := 0; i < rows; i++ {
		cells := len(pGen[i])
		r := make([]uint8, cells)
		for j := 0; j < cells; j++ {
			r[j] = calcNgCell(i, j, pGen)
			//fmt.Printf("- new val is: %d\r\n", r[j])
		}
		nGen[i] = r
	}
	return nGen
}

func calcNgCell(row int, col int, pGen Grid) uint8 {
	lvNbrs := countLiveNbrs(row, col, pGen)
	//fmt.Printf("cell {%d,%d} was {%d} (with %d live neighbors}", row+1, col+1, pGen[row][col], lvNbrs)
	currCellAlive := pGen[row][col] != 0
	if currCellAlive {
		if lvNbrs == 2 || lvNbrs == 3 {
			return 1
		} else {
			return 0
		}
	} else {
		if lvNbrs == 3 {
			return 1
		}
	}
	return 0
}

func countLiveNbrs(row int, col int, pGen Grid) int {
	count := 0
	rMin := max(row-1, 0)
	cMin := max(col-1, 0)
	rMax := min(row+1, len(pGen)-1)
	for r := rMin; r <= rMax; r++ {
		pRow := pGen[r]
		cMax := min(col+1, len(pRow)-1)
		for c := cMin; c <= cMax; c++ {
			if c == col && r == row {
			} else {
				if pGen[r][c] != 0 {
					count = count + 1
				}
			}
		}
	}
	//fmt.Printf("Cell {%d,%d} has %d live neighbors\r\n", row+1, col+1, count)
	return count
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
