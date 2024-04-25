function getTotal(어피치, 라이언){
    let 어피치점수 = 0;
    let 라이언점수 = 0;
    for(let i = 0; i <= 10; i++) {
        const k = 10 - i;
        if(어피치[i] == 0 && 라이언[i] == 0){
            continue;
        }
        if(어피치[i] >= 라이언[i]) 어피치점수 += k;
        else 라이언점수 += k;
    }
    return 라이언점수 - 어피치점수;
}

function comparator(a,b){
   return [...b].reverse().join("") - [...a].reverse().join("")
}

function solution(n,어피치) {
    const 라이언 = new Array(11).fill(0);
    var answer = [];
    let res = [];
    let mx = 0;
    
    function recur(cur, sum){
        if(sum > n) return;
        
        if(sum == n){
            const total = getTotal([...어피치], [...라이언])
           
            if(total > mx) {
                mx = total
                res = [[...라이언]]
            } else if(total == mx && total != 0){
                res = [...res, [...라이언]]
            }
            return;
        }
        if(cur > 10) return;
        
        for(let i = 0; i <= n; i++){
            라이언[cur] = i;
            recur(cur + 1, sum + i);
            라이언[cur] = 0;
        }
    }
    recur(0, 0)

    if(res.length == 0){
        return [-1]
    } else {
        return res.sort(comparator)[0]
    }
}