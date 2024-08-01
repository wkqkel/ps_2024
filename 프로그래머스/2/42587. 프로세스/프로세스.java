import java.util.*;
class Pair {
    int x;
    int y;
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class Solution {
    public int solution(int[] p, int l) {
        int n = p.length;
        Queue<Pair> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            q.add(new Pair(p[i], i));
        }
        int ret = 0;
        while(!q.isEmpty()){
            int mx = -1;
            int mx_idx = -1;
            int idx = 0;
            for(Pair cur : q){
                if(mx < cur.x){
                    mx = cur.x;
                    mx_idx = idx;
                }
                idx++;
            }
            for(int j = 0; j < mx_idx; j++){
                q.add(q.poll());
            }
            Pair cur = q.poll();
      
            ret++;
            if(cur.y == l) return ret;
        }
        
        
        return 0;
    }
}