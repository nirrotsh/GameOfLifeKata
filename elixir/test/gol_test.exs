defmodule GameOfLifeTest do
  use ExUnit.Case
  doctest GameOfLife

  test "init first-gen grid" do
  	exp = [
      [0,0,0,0,0,0,0,0],
      [0,0,0,1,0,0,0,0],
      [0,0,0,1,1,0,0,0],
      [0,0,0,0,0,0,0,0],
    ]
    fGen = GameOfLife.init(4,8, [{2,4}, {3,4}, {3,5}])
    assert exp == fGen
  end

  test "calc next generation from sample" do
   exp = [
      [0,0,0,0,0,0,0,0],
      [0,0,0,1,1,0,0,0],
      [0,0,0,1,1,0,0,0],
      [0,0,0,0,0,0,0,0],
    ]
    curGen = GameOfLife.init(4,8, [{2,5}, {3,4}, {3,5}])
    nextGen = GameOfLife.nextGen(curGen)
    assert exp == nextGen
  end

  test "rule test: Cells with fewer of two live neighbors dies" do
    curGen = GameOfLife.init(4,8, [{2,2}, {2,7}, {3,6}])
    nextGen = GameOfLife.nextGen(curGen)
    row2 = Enum.at(nextGen, 1)
    elNoNeighbours = Enum.at(row2, 1)
    elOneNeighbour = Enum.at(row2, 6)
    assert(elNoNeighbours==0, "Element with no neighbors should die")
    assert(elOneNeighbour==0, "Element with one neighbor should die")
  end

  test "rule test: Cell with more than three live neighbors dies" do
    curGen = GameOfLife.init(4,8, [{1,5}, {2,3}, {2,4}, {2,5}, {3,3}, {3,4}])
    nextGen = GameOfLife.nextGen(curGen)
    row2 = Enum.at(nextGen, 1)
    row3 = Enum.at(nextGen, 2)
    el5neighbors = Enum.at(row2, 3)
    el4Neighbours = Enum.at(row3, 3)
    assert(el5neighbors==0, "Element with 5 neighbors should die")
    assert(el4Neighbours==0, "Element with 4 neighbors should die")
  end

  test "rule test: Live cell with two neighbors stays alive" do
    curGen = GameOfLife.init(4,8, [{2,1}, {3,1}, {3,2}])
    nextGen = GameOfLife.nextGen(curGen)
    row3 = Enum.at(nextGen, 2)
    elLiveWith2LiveNeighbors = Enum.at(row3, 0)
    assert(elLiveWith2LiveNeighbors==1, "Live Element with exactly two neighbors should stay live")
  end

  test "rule test: Dead cell with exactly three neighbors becomes alive" do
    curGen = GameOfLife.init(6,6, [{1,2}, {2,1}, {2,2}])
    nextGen = GameOfLife.nextGen(curGen)
    row1 = Enum.at(nextGen, 0)
    elResurrected = Enum.at(row1, 0)
    assert(elResurrected==1, "Dead Element should become alive")
  end

  test "rule test: Dead cell with more than three neighbors stays dead" do
    curGen = GameOfLife.init(6,6, [{1,1}, {1,3}, {2,1}, {2,2}, {2,3}])
    nextGen = GameOfLife.nextGen(curGen)
    row1 = Enum.at(nextGen, 0)
    elStayDead = Enum.at(row1, 1)
    assert(elStayDead==0, "Dead Element with 5 neighbors should stay dead")
  end

end
