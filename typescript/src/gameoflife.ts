export type Coord = [number,number];   //One based row & column numbers

export type Grid = number[][];

export function init(rows : number, cols : number, liveCells :Coord[]) :Grid{
    let zeroGen : Grid= []; 
    for (let r=0; r<rows; r++){
        let row :number[] = [];
        for (let c=0; c<cols; c++) {
            row[c] =0;
        }
        zeroGen[r] = row;
    }
    
    liveCells.forEach(function(c :Coord) {
        zeroGen[c[0]-1][c[1]-1] =1;
    });

    return zeroGen;
}

export function getNextGen(prevGen: Grid) : Grid {
    let newGen : Grid= []; 
    for (let r=0; r < prevGen.length; r++){
        let newRow :number[] = []
        for (let c=0; c<prevGen[r].length; c++){
            newRow[c] = calcNextGenCell(r,c, prevGen);
        }
        newGen[r] = newRow;
    }
    return newGen
}

function calcNextGenCell(row: number,  col: number, prevGen :Grid) : number{
    let curAlive = prevGen[row][col] != 0;
    let liveNeighbors = calcLiveNeighbors(row,col,prevGen);
    if (curAlive && (liveNeighbors==2 || liveNeighbors==3))
        return 1;
    if (!curAlive && liveNeighbors==3)
        return 1;
    return 0;
}

function calcLiveNeighbors(row: number,  col: number, prevGen :Grid) : number {
    let count = 0;
    for (let r = row-1; r<=row+1; r++){
        if (r>=0 && r<prevGen.length){
            for (let c=col-1; c<=col+1; c++){
                if (c>=0 && c<prevGen[r].length) {
                    if (!(r==row && c==col) && prevGen[r][c]!=0){
                        count++;
                    }
                }
            }
        }
    }
    
    return count;
}