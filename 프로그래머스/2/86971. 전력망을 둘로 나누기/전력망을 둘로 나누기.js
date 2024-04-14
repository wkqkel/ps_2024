const par = [];
const sz = [];

function find(v){
    if(par[v] == v) return v;
    else return par[v] = find(par[v]);
}

function union(a, b){
    a = find(a);
    b = find(b);
    
    if(a == b) return;
    sz[b] += sz[a];
    par[a] = b;
}

function solution(n, wires) {
    let res = 1e9;
    for(let j = 0; j < wires.length; j++){
        for(let i = 1; i <= n; i++) {
            par[i] = i;
            sz[i] = 1;
        }
        
        for(let i = 0; i < wires.length; i++){
            if(i == j) continue;
            const [a,b] = wires[i];

            union(a,b);
        }
        const [a,b] = wires[j];
        const diff = Math.abs(sz[find(a)] - sz[find(b)]);
        res = Math.min(diff, res);
    }
    
    return res;
}