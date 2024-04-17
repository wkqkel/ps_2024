class Q {
    data = [];
    idx = 0;
    
    push(v){
        this.data.push(v);
    }
    
    length(){
        return this.data.length - this.idx;
    }
    
    front(){
        return this.data[this.idx];
    }
    
    pop(){
        return this.data[this.idx++];
    }
}

const dx = [1,0,-1,0];
const dy = [0,1,0,-1];
let n,m;

function bfs(sx,sy,ex,ey,maps){
    const ch = Array.from({length: n}, () => new Array(m).fill(false));
 
    const q = new Q();
    
    q.push([sx,sy]);
    ch[sx][sy] = 1;
    let d = 0;
    while(q.length()){
        let sz = q.length();
        while(sz--){
            const [x,y] = q.front();
            if(x == ex && y == ey) {
                return d;
            }
            q.pop();
          
            for(let i = 0; i < 4; i++){
                let nx = x + dx[i];
                let ny = y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(maps[nx][ny] == 'X' || ch[nx][ny]) continue;
                q.push([nx,ny]);
                ch[nx][ny] = true;
            }
        }
        d++;
    }
    return NaN;
}
function solution(maps) {
    n = maps.length;
    m = maps[0].length;
    let l,e,s;
    for(let i = 0; i < maps.length; i++){
        for(let j = 0; j < maps[0].length; j++){
              if(maps[i][j] == 'L') l = [i,j]
              if(maps[i][j] == 'E') e = [i,j];
              if(maps[i][j] == 'S') s = [i,j];
        }
    }
    
    const d = bfs(s[0], s[1], l[0], l[1], maps) + bfs(l[0], l[1], e[0], e[1],maps);
    
    return d || -1;
}