/** 
1. 아이디어
- 치킨집과 집의 좌표 배열을 각각 구함
- 치킨집의 m개 조합을 구함 -> dfs
- 조합을 돌면서, 집을 돌면서 치킨집을 돌면서, 
- 최소거리를 구하고, 도시거리를 구해서, 최소도시거리를 리턴함

2. 시간복잡도
- 
3. 자료구조
- chicken, house: 치킨집과 집의 좌표 - [int,int][]
- combi: [int,int][][]
- res: 각 조합별 도시치킨거리 배열
*/

const fs = require('fs');
const input = fs.readFileSync('dev/stdin').toString().trim().split('\n');

const [n,m] = input[0].split(' ').map(Number);
const map = input.slice(1).map(v=>v.split(' ').map(Number));

const house = [];
const chicken = [];

for(let i = 0; i < n;i++){
  for(let j = 0; j<n; j++){
    if(map[i][j]===1) house.push([i,j]);
    if(map[i][j]===2) chicken.push([i,j]);
  }
}

function combination(arr, m) {
  const answer = [];
  const temp = Array(m);
  
  function DFS(L, s) {
    if (L === m) answer.push(temp.slice());
    else {
      for (let i = s; i < arr.length; i++) {
        temp[L] = arr[i];
        DFS(L + 1, i + 1);
      }
    }
  }
  DFS(0, 0);

  return answer;
}


const combi = combination(chicken, m);

const res = [];

for(let i = 0; i<combi.length;i++) {
  let 도시의치킨거리 = 0
  for(let j = 0; j<house.length; j++) {
    const [x1,y1] = house[j];
    let 집의치킨거리 = 9999999
    for(let k = 0; k<combi[i].length;k++){
      const [x2,y2] = combi[i][k];
      const dist = Math.abs(x1-x2)+Math.abs(y1-y2);
      if(집의치킨거리 > dist) 집의치킨거리 = dist;
    }
    도시의치킨거리 += 집의치킨거리
  }
   res.push(도시의치킨거리)
}
console.log(res.sort((a,b)=>a-b)[0])

