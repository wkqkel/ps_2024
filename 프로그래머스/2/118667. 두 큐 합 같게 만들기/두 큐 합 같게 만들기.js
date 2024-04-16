class Q {
    data = [];
    cursor = 0;
    
    constructor(q){
        this.data = q;
    }
    
    push(v){
        this.data.push(v);
    }
    
    pop(){
        return this.data[this.cursor++];
    }
}

function solution(_q1, _q2) {
    // 반으로 만들어야함.
    const q1 = new Q(_q1);
    const q2 = new Q(_q2);
    const max = _q1.length + _q2.length;
    
    let sum1 = _q1.reduce((a,b)=> a+b);
    let sum2 = _q2.reduce((a,b)=> a+b);
    let cnt = 0;
    while(true){
        if(cnt > max  *2 ) return -1;
        if(sum1 > sum2){
            const v = q1.pop();
            q2.push(v);
            sum1 -= v;
            sum2 += v;
            cnt++;
        } else if(sum1 < sum2) {
            const v = q2.pop();
            q1.push(v);
            sum2 -= v;
            sum1 += v;
            cnt++;
        } else {
            return cnt;
        }
        
    }
}