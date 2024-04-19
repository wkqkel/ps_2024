class Q {
    data = [];
    idx = 0;
    push(v){
        this.data.push(v);
    }
    pop(){
        return this.data[this.idx++];
    }
    length(){
        return this.data.length - this.idx;
    }
}

const ch = Array.from({length: 102}, ()=> new Array(102).fill(0));

function bfs(sx,sy, board){
    const q = new Q();
    const n = board.length;
    const m = board[0].length;
    q.push([sx,sy]);
    ch[sx][sy] = 1;
    let sum = 0;
    while(q.length() > 0){
        let sz = q.length();
        while(sz--){
            const [x,y] = q.pop();
            sum += Number(board[x][y]);
            for(let [dx,dy] of [[1,0],[0,1],[-1,0],[0,-1]]){
                const nx = x + dx;
                const ny = y + dy;
             
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(board[nx][ny] == 'X' || ch[nx][ny]) continue;
                q.push([nx,ny]);
                ch[nx][ny] = 1;
            }
        }
    }
    return sum;
}

function solution(maps) {
   const res = [];

   for(let i = 0; i < maps.length; i++){
       for(let j = 0; j < maps[0].length; j++){
           if(maps[i][j] == 'X' || ch[i][j]) continue;
           res.push(bfs(i,j,maps));
        
       }
   }
 
    if(res.length == 0) {
        return [-1];
    }
    
    return res.sort((a,b)=> a-b)
}