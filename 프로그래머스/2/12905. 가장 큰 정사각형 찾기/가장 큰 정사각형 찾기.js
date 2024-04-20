function solution(b)
{
    const n = b.length;
    const m = b[0].length;
    
    const board = [];
    for(let i = 0; i <= n; i++){
        board[i] = [];
        for(let j = 0; j <= m ; j++){
            if(i == 0 || j == 0) board[i][j] = 0;
            else board[i][j] = b[i-1][j-1];
        }
    }
   
    const dp = Array.from({length: n+1}, ()=> Array(m+1).fill(0));

 
    for(let i = 1; i <= n; i++){
        for(let j = 1; j <= m; j++){
           if(board[i][j] == 0) dp[i][j] = 0;
            else dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1]) + 1;
        }
    }

    let mx = 0;
    for(let i = 0; i <= n; i++){
        for(let j = 0; j <= m; j++){
            mx = Math.max(dp[i][j], mx);
        }
    }
  
    return mx * mx;
}