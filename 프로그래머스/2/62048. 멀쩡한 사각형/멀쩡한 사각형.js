function solution(w, h) {
    const all = w * h;
    const cut = w + h - getGcd(w,h);
    return all - cut;
}

function getGcd(a,b){
    if(b == 0) return a;
    return getGcd(b, a % b)
}