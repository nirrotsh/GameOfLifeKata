package gameoflife

import "fmt"

import (
	"reflect"
	"testing"
)

func TestInit(t *testing.T) {
	fmt.Println("> Test: Initializing First Gen")
	exp := Grid{
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 1, 0, 0, 0, 0},
		{0, 0, 0, 1, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
	}

	liveCells := []Coord{{2, 4}, {3, 4}, {3, 5}}
	act := Init(4, 8, liveCells)
	if !reflect.DeepEqual(exp, act) {
		t.Errorf("Initialized array is incorrect. expected:\r\n%v\r\nbut got:\r\n%v", exp, act)
	}
}

func TestCalcNextGenFromProvidedSample(t *testing.T) {
	fmt.Println("> Test: Validating docunentation sample ")
	exp := Grid{
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 1, 1, 0, 0, 0},
		{0, 0, 0, 1, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
	}

	liveCells := []Coord{{2, 4}, {3, 4}, {3, 5}}
	curGen := Init(4, 8, liveCells)
	nGen := nextGen(curGen)
	if !reflect.DeepEqual(exp, nGen) {
		t.Errorf("Calculated Next Gen is incorrect. expected:\r\n%v\r\nbut got:\r\n%v", exp, nGen)
	}
}


func TestRuleLiveCellsWithLessThanTwoNeighborsShouldDie(t *testing.T) {
	fmt.Println("> Rule Tes: Live cell with less than two live neighbors should Die")
	curGen := Init(4, 8, []Coord{{2, 2}, {2, 7}, {3, 6}})
	nGen := nextGen(curGen)
	el0Nbr := nGen[1][1]
	el1Nbr := nGen[1][6]
	if el0Nbr != 0 {
		t.Error("Element {2,2} has zero live neighbors, and should have died")
	}
	if el1Nbr != 0 {
		t.Error("Element {2,7} has only one live neighbor, and should have died")
	}
}

func TestRuleLiveCellWithMoreThanThreeNeighborsShouldDie(t *testing.T) {
	fmt.Println("> Rule Tes: Live cell with more than three(3) live neighbors should Die")
	curGen := Init(4, 8, []Coord{{1, 5}, {2, 3}, {2, 4}, {2, 5}, {3, 3}, {3, 4}})
	nGen := nextGen(curGen)
	el4Nbr := nGen[2][3]
	el5Nbr := nGen[1][3]
	if el4Nbr != 0 {
		t.Error("Element {3,4} has four live neighbors, and should have died")
	}
	if el5Nbr != 0 {
		t.Error("Element {2,4} has five live neighbors, and should have died")
	}
}

func TestRuleLiveCellWithTwoLiveNeighborsStayAlive(t *testing.T) {
	fmt.Println("> Rule Tes: Live cell with two live neighbors should stay alive")
	curGen := Init(4, 8, []Coord{{2, 1}, {3, 1}, {3, 2}})
	nGen := nextGen(curGen)
	el2Nbr := nGen[2][0]
	if el2Nbr == 0 {
		t.Error("Element {3,1} has two live neighbors, and should stay alive")
	}
}

func TestRuleDeadCellWithThreeLiveNeighborsShouldBecomeAlive(t *testing.T) {
	fmt.Println("> Rule Tes: Dead cell with exactly Three(3) live neighbors should become alive")
	curGen := Init(6, 6, []Coord{{1, 2}, {2, 1}, {2, 2}})
	nGen := nextGen(curGen)
	el2Nbr := nGen[0][0]
	if el2Nbr == 0 {
		t.Error("Dead Element {1,1} has Three live neighbors, should become alive")
	}
}

func TestRuleDeadCellWithMoreThanThreeLiveNeighborsShouldStayDead(t *testing.T) {
	fmt.Println("> Rule Tes: Dead cell with exactly Three(3) live neighbors should become alive")
	curGen := Init(6, 6, []Coord{{1, 1}, {1, 3}, {2,1}, {2, 2}, {2,3}})
	nGen := nextGen(curGen)
	el2Nbr := nGen[0][1]
	if el2Nbr == 1 {
		t.Error("Dead Element {1,2} has Five live neighbors, should stay dead")
	}
}
