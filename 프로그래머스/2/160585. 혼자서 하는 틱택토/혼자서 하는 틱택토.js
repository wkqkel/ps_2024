function isWin(board, target){
    // 가로
    let winRow = 0;
    for(let i = 0; i < 3; i++){
        if(board[i][0] == target 
           && board[i][0] == board[i][1] 
           && board[i][0] == board[i][2]) 
            winRow++;
    }
    // 세로
    for(let i = 0; i < 3; i++){
        if(board[0][i] == target 
           && board[0][i] == board[1][i] 
           && board[0][i] == board[2][i]) 
             winRow++;
    }
    // 대각선
    if(board[0][0] == target 
       && board[0][0] == board[1][1] 
       && board[0][0] == board[2][2]) 
           winRow++;
    
    if(board[0][2] == target 
       && board[0][2] == board[1][1] 
       && board[0][2] == board[2][0]) 
           winRow++;
    
    return winRow;
}

function solution(board) {
    let oCnt = 0;
    let xCnt = 0;
    let dCnt = 0;
    for(let i = 0; i < 3; i++){
        for(let j = 0; j < 3; j++){
            if(board[i][j] == 'O') oCnt++;
            if(board[i][j] == 'X') xCnt++;
            if(board[i][j] == '.') dCnt++;
        }
    }
    
    const diff = oCnt - xCnt
    if(diff > 1 || diff < 0) return 0;
    if(oCnt == 0 && xCnt > 0) return 0;
    if(isWin(board,'O') && isWin(board,'X')) return 0;

    // o가 이겼는데 계속 두는 상황
    if(isWin(board,'O') == 1 && xCnt >= oCnt) 
        return 0; 
    // x가 이겼는데 계속 두는 상황
    if(isWin(board,'X') == 1 && xCnt < oCnt && dCnt)
        return 0;
    
    return 1;
}