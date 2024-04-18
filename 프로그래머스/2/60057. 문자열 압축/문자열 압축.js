function solution(str) {
    const n = str.length;
    let mn = 1e9;
    
    for(let i = 1; i <= n; i++){
        let s = 0;
        let e = i;
        let cur, nxt;
        let cnt = 1;
        let res = "";
        while(cur != ''){
            cur = str.slice(s, e);
            nxt = str.slice(s+i, e+i);
            if(cur == nxt) cnt++;
            else {
                if(cnt!= 1) res += cnt;
                res += cur;
                cnt = 1;
            }
            s+=i;
            e+=i;
        }
 
        mn = Math.min(mn, res.length);
    }
    return mn;
}