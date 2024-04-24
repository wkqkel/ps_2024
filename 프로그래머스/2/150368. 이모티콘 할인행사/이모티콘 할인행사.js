/**
1. 이모티콘마다 할인율 경우의 수를 구한다.
4(할인수) ^ 7(이모티콘수)
2. users들마다 이모티콘에 대해서 구매비용과 플러스여부를 계산한다
100(유저수) * 7(이모티콘수) 
*/

function solution(users, emoticons) {
    const arr = [];
    const res = [];
    function recur(cur){
        if(cur == emoticons.length) {
            let 판매액 = 0;
            let 가입자수 = 0;
            
            for(const user of users){
                const [비율기준, 구매기준] = user;
                let plus = false;
                let 구매가 = 0;
                for(let i = 0; i < emoticons.length; i++){
                  const 할인비율 = arr[i];
                  if(할인비율 < 비율기준) continue;
                  const 할인된가격 = emoticons[i] * (100 - 할인비율) / 100;
                  구매가 += 할인된가격;
                  if(구매가 >= 구매기준) {
                      구매가 = 0;
                      plus = true;
                      break;
                  }
                }
                if(plus){
                    가입자수++
                } else {
                    판매액 += 구매가;
                }
            }
            res.push([가입자수,판매액])
            return;
        }
        for(let 할인율 of [10,20,30,40]){
            arr[cur] = 할인율;
            recur(cur+1);
        }
    }
    recur(0);
    
    return res.sort((a,b)=> {
        if(b[0]-a[0])return b[0] - a[0]
        else return b[1] - a[1];
    })[0]
}

