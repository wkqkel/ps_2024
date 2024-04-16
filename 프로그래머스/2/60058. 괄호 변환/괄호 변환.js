function isBalance(w){
    if(w.length == 0) return false;
    
    let cnt1 = 0;
    let cnt2 = 0;
    
    for(let c of w){
        if(c == '(') cnt1++;
        else cnt2++;
    }

    return cnt1 === cnt2;
}

function isCorrect(w){
    const s = [];
    if(!isBalance(w)) return false;
    for(let c of w){
        if(c == '(') s.push(c);
        else if(c == ')' && s[s.length-1] =='('){
            s.pop();
        }
        else return false;
    }
    
    return true;
}

function reverse(w){
    let res = "";
    for(let c of w){
        if(c == '(') res += ")";
        else res += '(';
    }
    return res;
}

function recur(w){
    if(w === '') return w;
 
    let u = "";
    let v = [...w];
    
    while(!isBalance(u)){
      u += v.splice(0,1);
    }
    v = v.join("");
    
    if(isCorrect(u)) {
        u += recur(v);
        return u;
    } else {
        let str = "(";
        str += recur(v);
        str += ")";
        u = [...u].slice(1, u.length - 1).join("");
        str += reverse(u);
        return str;
    }
}

function solution(p) {

    return recur(p)
}