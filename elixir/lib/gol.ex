defmodule GameOfLife do
  @moduledoc """
  Conway's Game Of Life Kata
  """

  @doc """
  Generates the initial finit grid with live and dead cells
  """
  def init(rows, cols, liveCells) do
    grid = initBlankGrid(rows, cols)
    setLiveCells(grid, liveCells)
  end

  def nextGen(grid) do
    curGen = getLiveCellsMap([], 1, grid)
    buildNextGen(1,grid, [], curGen)
  end

  defp initBlankGrid(rows, cols) do
    for _ <- 1..rows, do: List.duplicate(0, cols)
  end

  defp setLiveCells(grid, []) do
    grid
  end

  defp setLiveCells(grid, [head | tail]) do
    {row, col} = head
    newGrid = setCell(grid, row, col, true)
    setLiveCells(newGrid, tail)
  end

  defp setCell(grid, row, col, isLive) do
    newVal = if (isLive), do: 1, else: 0
    newRow = grid
      |> Enum.at(row-1)
      |> List.replace_at(col-1, newVal)
    List.replace_at(grid, row-1, newRow)
  end

  defp getLiveCellsMap(liveCells, _, []) do
    liveCells
  end

  defp getLiveCellsMap(liveCells, rowId, [head | tail]) do
    cells = head
      |> Enum.with_index(1)
      |> Enum.map(fn {v,i} -> {{rowId,i},v} end)
      |> Enum.filter(fn {_,v} -> v==1 end)
      |> Enum.map(fn {p,_} -> p end)
      getLiveCellsMap(liveCells ++ cells, rowId+1, tail)
  end

  defp buildNextGen(_,[], nxtGen, _) do
    nxtGen
  end

  defp buildNextGen(row ,[head | tail], nxtGen, curGen) do
    rowNg = traverseCRowCells(row, 1, head, [], curGen)
    buildNextGen(row+1, tail, nxtGen ++ [rowNg], curGen)
  end

  defp traverseCRowCells(_, _, [], nxtGen, _) do
    nxtGen
  end

  defp traverseCRowCells(row, col, [head | tail], nxtGen, curGen) do
    currCell = head
    lvNbrs = countLiveNeighbors(row,col, curGen)
    ngCell = calculateNgCell(currCell, lvNbrs)
    traverseCRowCells(row, col+1, tail, nxtGen ++ ngCell, curGen)
  end

  defp countLiveNeighbors(row,col,curGen) do
    all9 = for r <- (row-1..row+1), c <- (col-1)..(col+1), do: {r,c}
    nbrs = Enum.filter(all9, fn e -> e != {row,col} end)
    liveNbrs = Enum.filter(nbrs, fn n -> Enum.member?(curGen,n) end)
    Enum.count(liveNbrs)
  end


  defp calculateNgCell(currCell, lvNbrs) do
    cond do
      (currCell==1 && lvNbrs <2) ->
        [0]
      (currCell==1 && lvNbrs >3) ->
        [0]
      (currCell==1) ->
        [1]
      (currCell==0 && lvNbrs == 3) ->
        [1]
      true -> [0]
    end
  end
end
