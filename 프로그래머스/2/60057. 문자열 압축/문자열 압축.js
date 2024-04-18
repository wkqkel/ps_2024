function solution(s) {
    const n = s.length;
    let mn = 1e9;
    for(let i = 1; i <= n; i++){
        let res = ""
        let prev = s.slice(0, i);
        let cnt = 1;
 

        for(let j = i; j < n; j+= i){
            const cur = s.slice(j, j+i);
     
            if(prev == cur) cnt++;
            else {
                if(cnt != 1) res += cnt
                res += prev;
                prev = cur;
         
                cnt = 1;
            }
        }
        if(cnt != 1) res += cnt
     
        res += prev;
        
        mn = Math.min(mn, res.length);
    }
    return mn;
}