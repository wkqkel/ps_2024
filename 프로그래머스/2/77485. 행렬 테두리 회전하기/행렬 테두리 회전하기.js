const dirs = [[0,1],[1,0],[0,-1],[-1,0]];
let board = [];

function rotate(query){
   let mn = 1e9;
   const [x1, y1, x2, y2] = query;
   let dir = 0;
   let x = x1;
   let y = y1;
    
   let prev = board[x][y];
   while(dir != 4){
       const nx = x + dirs[dir][0];
       const ny = y + dirs[dir][1];
       if(nx < x1 || nx > x2 || ny < y1 || ny > y2) {
           dir++
           continue;
       }
       const tmp = board[nx][ny];
       board[nx][ny] = prev;
       prev = tmp;
       mn = Math.min(mn, tmp);
       x = nx;
       y = ny;  
   }
    
    return mn;
}

function solution(rows, columns, queries) {
    var answer = [];

    for(let i = 1; i <= rows; i++){
        board[i] = []
        for(let j = 1; j <= columns; j++){
            board[i][j] = (i-1) * columns+j;
        }
    }
    
    for(const q of queries){
        answer.push(rotate(q));
    }
    
    return answer;
}