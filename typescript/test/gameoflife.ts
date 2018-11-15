import { expect } from 'chai';
import {init, getNextGen} from '../src/gameoflife';

describe ('Game Of Life', () =>{
    it ('return the correct initiated (zeroGen) Grid', ()=>{
        const expected = [
            [0,0,0,0,0,0,0,0],
            [0,0,0,1,0,0,0,0],
            [0,0,0,1,1,0,0,0],
            [0,0,0,0,0,0,0,0]
        ];
        const actual = init(4,8, [[2,4], [3,4],[3,5]]);
        expect(compareArrays(actual, expected)).to.true;
    });

    it ('Should correctly calculate the first gen from the provided sample', ()=>{
        const expected = [
            [0,0,0,0,0,0,0,0],
            [0,0,0,1,1,0,0,0],
            [0,0,0,1,1,0,0,0],
            [0,0,0,0,0,0,0,0]
        ];
        const zeroGen = init(4,8, [[2,4], [3,4],[3,5]]);
        const firstGen = getNextGen(zeroGen);
        expect(compareArrays(firstGen, expected)).to.true;
    });

    it ('Rule Test: Live cell with less of two live neighbors should die', () =>{
        const zeroGen = init(4,8, [[2,2], [2,7],[3,6]]);
        const fGen = getNextGen(zeroGen);
        expect(fGen[1][1]).to.equal(0);
        expect(fGen[1][6]).to.equal(0);
    });

    it ('Rule Test: Live cell with More than three live neighbors should die', () =>{
        const zeroGen = init(4,8, [[1,5], [2,3],[2,4],[2,5],[3,3],[3,4]]);
        const fGen = getNextGen(zeroGen);
        expect(fGen[2][3]).to.equal(0);
        expect(fGen[1][3]).to.equal(0);
    });

    it ('Rule Test: Live cell with exactly two live neighbors should stay alive', () =>{
        const zeroGen = init(4,8, [[2,1], [3,1],[3,2]]);
        const fGen = getNextGen(zeroGen);
        expect(fGen[2][0]).to.equal(1);
    });

    it ('Rule Test: Dead cell with exactly three live neighbors should become alive', () =>{
        const zeroGen = init(6,6, [[1,2], [2,1],[2,2]]);
        const fGen = getNextGen(zeroGen);
        expect(fGen[0][0]).to.equal(1);
    });
    
    it ('Rule Test: Dead cell with more than three live neighbors should stay dead', () =>{
        const zeroGen = init(6,6, [[1,1], [1,3],[2,1],[2,2],[2,3]]);
        const fGen = getNextGen(zeroGen);
        expect(fGen[0][1]).to.equal(0);
    });
});

function compareArrays(a : number[][], b: number[][]) : Boolean{
    if (!a || !b)
        return false;
    if (a.length != b.length)
        return false;
    for (let r =0; r<a.length; r++){
        let ra = a[r];
        let rb = b[r];
        if (ra.length != rb.length)
            return false;
        for (let c=0; c<ra.length; c++) {
            if (ra[c]!=rb[c])
                return false;
        }
    }
    return true;
}