function getCnt(time, times){
    return times.reduce((acc,cur)=> {
       return acc + Math.floor(time / cur)
    }, 0)
}
function solution(n, times) {
    let s = 1;
    let e = 1e20;
   
    
    while(s + 1 < e){
        const mid = Math.floor((s+e)/2);
        const cnt = getCnt(mid, times);
        
        if(cnt >= n) {
            e = mid;
        } else {
            s = mid;
        }
    }
    
    return e;
}