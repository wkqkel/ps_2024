/**
쿼리 1e5

경우의 수 2^6에 대해 배열 정렬해두고, => 108
거기서 이진탐색으로 lowerBound 점수만 찾아서 카운트

틀려서 왜 이게 틀리지 하고 다른 사람꺼 찾아봤는데, 
풀어나가는 방식은 똑같은데 나는 시간초과뜨고 그 사람은 안뜸..

살펴보니
if(map[key]) map[key].push(점수);
else map[key] = [점수];
부분과
map[key] = (map[key]||[]).concat(점수);
부분에서 차이가 있었음.

concat 메서드는 
기존 배열을 복사한 후 원소를 추가하며 새 배열을 리턴하기때문에 
성능적으로 최대 945배 느리다고함(gpt왈)
*/
function getLowerBound(arr, find){
    let s = 0;
    let e = arr.length - 1;
        
    while(s <= e){
      let mid = Math.floor((s+e)/ 2);
  
      if(arr[mid] < find){
        s = mid + 1;
      } else {
        e = mid - 1;
      }
    }
    
    if(s >= arr.length){
        return arr.length;
    }
    
    return s;
}
function solution(infos, query) {
    const map = {}
    for(let info of infos){
        const [언어, 직군,경력,음식,점수] = info.split(" ");
        const keys = [언어, 직군,경력,음식];
        const tmp = [];
        function recur(cur){
            if(cur == 4){
                const key = tmp.join("")
                if(map[key]) map[key].push(점수);
                else map[key] = [점수];
                return;
            }
            tmp[cur] = keys[cur];
            recur(cur+1);
            tmp[cur] = '-';
            recur(cur+1)
        }
        recur(0)
    }
    for(let k in map){
        map[k] = map[k].map(Number).sort((a,b)=> a-b);
    }

 
    const res = [];
    for(const q of query){
        const [언어, 직군,경력,음식,점수] = q.replace(/ and/g, '').split(" ");
        const key = [언어,직군,경력,음식].join("");

        const arr = map[key];
        if(!arr) {
            res.push(0);
            continue;
        }
        const lowerBound = getLowerBound(arr, 점수);
        res.push(map[key].length - lowerBound);
    }
    
    return res;
}