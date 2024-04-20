/**
특정값이 최소일 때 제거해야하는 바위의 갯수를 찾고
해당 갯수가 n보다 크면 최솟값을 줄이고,
같거나 작으면 늘려야함.

못풀어서 https://mjmjmj98.tistory.com/78 참고
*/
function solution(distance, rocks, n) {
    const arr = [0, ...rocks.sort((a,b)=>a-b), distance];
    
    let s = 1;
    let e = distance + 1;
    
    while(s + 1 < e){
        const mid = Math.floor((s + e) / 2);
        let cnt = 0;
        let prev = arr[0];
        for(let i = 1; i < arr.length; i++){
            if(arr[i] - prev < mid){
                cnt++
            } else {
                prev = arr[i];
            }
        }
        if(cnt > n){
            e = mid;
        } else {
            s = mid;
        }
    }
    
    return s; 
}