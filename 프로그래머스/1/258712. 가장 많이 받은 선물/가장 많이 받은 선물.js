function solution(friends, gifts) {
    const name_idx_map = friends.reduce((acc,cur,idx)=> ({...acc,[cur]:idx}), {});

    const [n, m] = [friends.length, gifts.length];

    const 선물기록 = Array.from({length: n}, ()=> Array(n).fill(0));
    for(let i = 0; i < m; i++){
       const [sender, receiver] = gifts[i].split(' ');
       const from = name_idx_map[sender];
       const to = name_idx_map[receiver];
       선물기록[from][to] = 선물기록[from][to] + 1;
    }
    
    const 선물지수 = Array(n).fill(0);
    for(let i = 0; i < m; i++){
       const [sender, receiver] = gifts[i].split(' ');
       const s_idx = name_idx_map[sender];
       const r_idx = name_idx_map[receiver];
       선물지수[s_idx] = 선물지수[s_idx] + 1;
       선물지수[r_idx] = 선물지수[r_idx] - 1;
    }
    
    const 다음달 = Array(n).fill(0);
    for(let i = 0; i < n; i++){
        for(let j = 0; j < n; j++){
             if(선물기록[i][j] > 선물기록[j][i]) {
                 다음달[i] = 다음달[i] + 1;
             }      
             else if(선물기록[i][j] == 선물기록[j][i] && 선물지수[i] > 선물지수[j]){
                    다음달[i] = 다음달[i] + 1; 
             }
        }
    }
   
    let max = -1;
    for(let i = 0; i < n; i++){
        if(다음달[i] > max) max = 다음달[i];
    }
  
    return max;
}