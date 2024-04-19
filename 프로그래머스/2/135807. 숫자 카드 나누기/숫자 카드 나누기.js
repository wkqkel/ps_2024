function getGcd(a, b){
    if(a < b) {
        let tmp = a;
        a = b;
        b = tmp;
    }
    
    while(true){
        let c = Math.floor(a % b);
        if(c == 0) break;
        a = b;
        b = c;
    }
    
    return b;
}

function getD(v){
    const res = []
    for(let i = 1; i * i <= v; i++){
        if(v % i == 0){
            res.push(i);
            if(v /i != i)res.push(v / i);
        }
    }
    return res.sort((a,b)=> a-b);
}


function solution(A, B) {
  let a1 = A.reduce(getGcd)
  let b1 = B.reduce(getGcd)
  
  let mx = 0;
    
  for(let d of getD(a1)){
      if(B.every(v=> v %d != 0)) mx = Math.max(d, mx);
  }
    
  for(let d of getD(b1)){
      if(A.every(v=> v %d != 0)) mx = Math.max(d, mx);
  }
    
  return mx;
}