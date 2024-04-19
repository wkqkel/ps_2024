const MAP = {"diamond": 0, "iron": 1, "stone": 2};
const 피로도 = [[1,1,1],[5,1,1],[25,5,1]];

const arr = [];
let life = 5;

function solution(picks, minerals) {
    const n = minerals.length
    let mn = 1e9;
  
    function recur(cur){
        if(cur ==  Math.ceil(n / 5) || picks.every(v=> v<=0)){
            let len = Math.min(n, cur * 5);
            let sum = 0;
            for(let i = 0; i < len; i++){
              sum += 피로도[arr[i]][MAP[minerals[i]]]
            }
            mn = Math.min(mn, sum);
            return;
        }
        
        for(let i = 0; i < 3; i++){
            if(picks[i] <= 0) continue;
            for(let j = 0; j < 5; j++){
                arr[(cur * 5) + j] = i;
            }
            picks[i]--;
            recur(cur+1);
            picks[i]++;
        }
    }  
    
    recur(0)
    return mn;
}