const dx = [1,0,-1,0];
const dy = [0,1,0,-1];

function isPerson(x,y,board,no){
     for(let i = 0; i < 4; i++){
        const nx = x + dx[i];
        const ny = y + dy[i];
      
        if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
        if(no && nx == no[0] && ny == no[1]) continue;
        if(board[nx][ny] == 'P') {
            return true;
        }
    }
    return false;
}

function isPass(x,y,board){
    if(isPerson(x,y,board,[x,y])){
         return false;
    }
    
    for(let i = 0; i < 4; i++){
        const nx = x + dx[i];
        const ny = y + dy[i];
        if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
        if(board[nx][ny] == 'O' && isPerson(nx,ny,board,[x,y])) {
            return false;
        }
    }
    
    return true;
}

function solution(places) {
    const res = []
    // const place = ["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"]
    for(let place of places){
        let flag = true;
        for(let i = 0; i < 5; i++){
            for(let j = 0; j < 5; j++){
                if(place[i][j] == 'P' && !isPass(i,j, place)){
                    flag = false;
                }
            }
        }
        res.push(+flag)
    }
    
    return res;
}