def init(rows, cols, liveCells):
    grid=[]
    for r in range(0, rows):
        row = []
        for c in range(0,cols):
            row.append(1) if (r+1,c+1) in liveCells else row.append(0)
        grid.append(row)
    return grid

def nextGen(grid):
    ngGrid = []
    r = 0
    for row in grid:
        ngRow = []
        c=0
        for col in row:
            ngRow.append(calcNgCell(r,c, grid))
            c = c+1
        ngGrid.append(ngRow)
        r = r+1
    return ngGrid

def calcNgCell(r,c, grid):
    isAlive = grid[r][c] != 0
    liveNeighbors = calcLiveNeighbors(r,c,grid)
    if (isAlive and (liveNeighbors==2 or liveNeighbors==3)):
        return 1
    if (not isAlive and liveNeighbors==3):
        return 1
    return 0

def calcLiveNeighbors(row,col,grid):
    count = 0
    for r in (row-1, row, row+1):
        for c in (col-1, col, col+1):
            if (r>=0 and r<len(grid) and c>=0 and c<len(grid[r]) and (not(r==row and c==col))):
                if (grid[r][c] != 0):
                    count = count +1
    return count
