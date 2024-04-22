class Q{
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

function solution(begin, target, words) {
    const ch = [];
    res = bfs(begin) || 0;
    function canChange(word,cur){
        let cnt = 0;
        for(let i = 0; i < word.length; i++){
            if(word[i] != cur[i]) cnt++; 
        }
        return cnt == 1;
    }
    function bfs(s){
        const q = new Q();
        q.push([s,0])
        
        while(q.size()>0){
            const [cur,dep] = q.pop();
          
            if(cur == target){
                return dep;
            }
            
            for(let i =0; i < words.length; i++){
                const word = words[i];
                if(!canChange(word, cur) || ch[i]) continue;
                q.push([word, dep+1]);
                ch[i] = 1;
            }
        }
    }
    
    return res;
}