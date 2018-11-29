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
