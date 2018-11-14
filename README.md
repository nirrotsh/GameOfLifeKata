# Game Of Life Kata
##### Game Of Life Kata - done in multiple languages.
***
This is based on the [Conway's Game Of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) but with a finite grid.

The base requirements for this Kata were taken from [Coding Dojo](http://codingdojo.org/kata/GameOfLife/)

The basic concept here was to do the same Kata, but in multiple different languages. I kept the same set of unit tests and (mostly) the same API accross all the implementations. 
This means that the implementation may not be optimal for each language.
E.g. Some languages do not support Arrays at all, or they support only single-dimention arrays - So I used a Jagged Array (Array of Arrays) to represent the finite M x N grid.

This however allows you to compare how the same implementation looks at each language - and basically "compare apples to apples".

### Kata description
You get as input an M * N initial grid where some cells are marked as “LIVE” and the other are marked as “DEAD”.
This is considered as the "Zero Generation"

##### Input sample

GO
```golang
zeroGen := Init(4, 8, []Coord{{2, 4}, {3, 4}, {3, 5}} )
```

C#
```csharp
var gol = new GameOfLife(4, 8, new Coord[] { new Coord(2, 4), new Coord(3, 4), new Coord(3, 5) });
int[][] zeroGen = gol.CurrentGeneration;
```

This initiate a 4 rows * 8 columns grid, with 3 “LIVE” cells (row 2, col 5, row 3 col 4 & 5) like the below:
```
. . . . . . . .
. . . . * . . .
. . . * * . . .
. . . . . . . .
```
 (Where “.” Marks “DEAD” cell, “*” marks LIVE cell)

##### The Excersize Objective
To calculate the next generation of the grid – based on the following rules:
1.	A cell “neighbor” is any of the 8 cells that surround it, directly below, above, to left, to right or diagonally.
2.	No “life” can exist outside of grid bounds (i.e. any cell coordinates outside of the grid are considered dead)
3.	Any live cell with fewer than two (2) live neighbors dies.
4.	Any live cell with more than three (3) live neighbors dies.
5.	Any live cell with two or three (3) live neighbors lives on to the next generation.
6.	Any dead cell with exactly three (3) live neighbors becomes a live cell.

So assuming the input above, the output should be:

Go
```golang
firstGen := nextGen(zeroGen)
```

C#
```csharp
int[][] firstGen = gol.NextGen();
```

And the resulting grid will look like this:
```
. . . . . . . .
. . . * * . . .
. . . * * . . .
. . . . . . . .
```
