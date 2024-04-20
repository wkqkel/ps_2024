let arr = [];
const ch = {};
let res = 0;


function has(a, b){
    return [...a].every(v=> b.includes(v))
}



function recur(cur, st, n, r){
    if(cur == n) {
        const cur = arr.join("");
         // console.log({arr,n})
        if(Object.keys(ch).some(k=> has(k, cur))) return;
       
        const d = r.map(v=>v.filter((_,i)=> arr.includes(i)).join(""))
       
        if(d.length == new Set(d).size){
            res++;
            ch[cur] = 1;
        }
   
        return;
    }
    
    for(let i = st; i < r[0].length; i++){
        arr[cur] = i;
        recur(cur + 1, i+1 , n, r);
    }
}
// 0이 됐으면 01은안돼
// 12는되고, 13도 됨. 123은 안됨.
// 145가 됨. 1245가 있으면 안됨.
function solution(r) {
    // console.log(has("145","1245"), true)
    // console.log(has("12", "13"), false)
    // console.log(has("12", "123"), true)
    // console.log(has("0", "01"),true)
    for(let i = 1; i <= r[0].length; i++){
        recur(0, 0, i, r);
    }
    
    return res;
}