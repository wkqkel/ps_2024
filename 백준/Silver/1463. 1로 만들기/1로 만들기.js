/** 
- BOS 1463: 1로 만들기

1. 테이블 정의하기
D[i] 는 i를 1로 만들기 위해 필요한 연산 사용 횟수의 최솟값 

2. 점화식 찾기
D[12] = ?
3으로 나누거나 (D[12] = D[4] + 1)
2로 나누거나 (D[12] = D[6] + 1)
1을 빼거나 D[12] = D[11] + 1
-> D[12] = min(D[4] + 1, D[6] + 1, D[11] + 1) 

비슷한 방법으로 D[k] = ?
3으로 나눠지면 D[k] = D[k/3] + 1
2로 나누어지면 D[k] = D[k/2] + 1;
1을 빼거나 D[k] = D[k-1] + 1 중 최솟값

3. 초기값 정의하기
D[1] = 0
매번 점화식으로 돌아가게 하는 초기값이 어디까지인가 고민해야함
*/

const fs = require('fs');
const input = fs.readFileSync('dev/stdin').toString().trim();
const n = +input;

const D = [];
D[1] = 0;

for(let i = 2; i <= n; i++) {
   D[i] = D[i-1] + 1;
  if(i % 3 === 0) {
    D[i] = Math.min(D[i], D[i/3] + 1);
  }
  if(i % 2 === 0) {
    D[i] = Math.min(D[i], D[i/2] + 1);
  }
}

console.log(D[n])