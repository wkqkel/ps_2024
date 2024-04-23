function solution(tickets) {
    const res = [];
    const ch = {};
    const graph = tickets.reduce((acc,cur)=>{
        const [from, to] = cur; 
        ch[from+to] =  (ch[from+to] || 0) + 1
        return {...acc, [from]: [...(acc[from]||[]), to]} 
    },{})
  
    dfs("ICN", 0, ["ICN"]);
 
    function dfs(cur, i, d){
        if(i == tickets.length){
            res.push(d);
            return;
        }

        for(const nxt of (graph[cur]||[])){
            if(ch[cur + nxt] <= 0) continue;
            ch[cur+nxt]--;
            dfs(nxt, i+1, [...d, nxt])
            ch[cur+nxt]++;
        }
    }
    
    return res.sort()[0];
}