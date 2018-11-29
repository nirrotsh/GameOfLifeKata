from gameoflife import init, nextGen

def test_correct_zerogen():
    exp = [[0,0,0,0,0,0,0,0],
        [0,0,0,1,0,0,0,0],
        [0,0,0,1,1,0,0,0],
        [0,0,0,0,0,0,0,0]]
    act = init(4,8, [(2,4),(3,4),(3,5)])
    assert act==exp

def test_sample_next_gen():
    exp = [[0,0,0,0,0,0,0,0],
        [0,0,0,1,1,0,0,0],
        [0,0,0,1,1,0,0,0],
        [0,0,0,0,0,0,0,0]]
    zeroGen = init(4,8, [(2,4),(3,4),(3,5)])
    nGen = nextGen(zeroGen)
    assert exp == nGen

def test_live_cells_with_less_then_two_neighbors_should_die():
    zeroGen = init(4,8, [(2,2), (2,7),(3,6)])
    fGen = nextGen(zeroGen)
    assert fGen[1][1] == 0
    assert fGen[1][6] == 0

def test_live_cells_with_more_then_three_neighbors_should_die():
    zeroGen = init(4,8, [(1,5), (2,3),(2,4),(2,5),(3,3),(3,4)])
    fGen = nextGen(zeroGen)
    assert fGen[2][3] == 0
    assert fGen[1][3] == 0

def test_live_cells_with_two_live_neighbors_should_stay_alive():
    zeroGen = init(4,8, [(2,1), (3,1),(3,2)])
    fGen = nextGen(zeroGen)
    assert fGen[2][0] == 1

def test_dead_cells_with_exactly_three_live_neighbors_should_become_alive():
    zeroGen = init(6,6, [(1,2), (2,1),(2,2)])
    fGen = nextGen(zeroGen)
    assert fGen[0][0] == 1

def test_dead_cells_with_more_than_three_live_neighbors_should_stay_dead():
    zeroGen = init(6,6, [(1,1), (1,3),(2,1),(2,2),(2,3)])
    fGen = nextGen(zeroGen)
    assert fGen[0][1] == 0