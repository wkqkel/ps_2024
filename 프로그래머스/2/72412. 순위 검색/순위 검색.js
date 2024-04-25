/**
쿼리 1e5

경우의 수 2^6에 대해 배열 정렬해두고, => 108
거기서 이진탐색으로 upperBound 점수만 찾아서 카운트
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