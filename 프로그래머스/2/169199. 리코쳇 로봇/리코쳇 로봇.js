class Q {
    data = [];
    idx = 0;
    push(v){
        this.data.push(v);
    }
    pop(){
        return this.data[this.idx++];
    }
    size(){
        return this.data.length - this.idx;
    }
}

function solution(board) {
    const [n,m] = [board.length, board[0].length];
    const ch = Array.from({length: n},()=> Array(m).fill(0));
    let s, e;
    let res = 1e9;
    
    for(let i = 0; i < n; i++){
        for(let j = 0; j < m; j++){
            if(board[i][j] == 'R') s = [i,j]
            if(board[i][j] == 'G') e = [i,j]
        }
    }
    
    bfs(s[0],s[1],0)
    function bfs(sx, sy){
        const q = new Q();
        q.push([sx,sy,0]);
        ch[sx][sy] = 1;
        
        while(q.size() > 0){
            let sz = q.size();
            while(sz--){
                const [x,y,dep] = q.pop();
                
                if(x == e[0] && y == e[1]) {
                    res = Math.min(res, dep);
                }
                for(let [dx,dy] of [[1,0],[0,1],[-1,0],[0,-1]]){
                   let nx = x, ny = y;
                   while(true){
                        nx += dx;
                        ny += dy;  
                        if(nx < 0 || nx >= n || ny < 0 || ny >= m 
                           || board[nx][ny] == 'D') {
                            nx -= dx;
                            ny -= dy;
                            break;
                        }
                    }
                    if (ch[nx][ny]) continue;
                    q.push([nx,ny,dep+1]);
                    ch[nx][ny] = 1;
                }
            }
        }
    }
    if(res == 1e9) return -1;
    return res;
}