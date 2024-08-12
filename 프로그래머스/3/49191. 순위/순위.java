import java.util.*;

class Solution {
    final int MX = 120;
    boolean ch1[] = new boolean[MX];
    boolean ch2[] = new boolean[MX];
    List<Integer> vec1[] = new ArrayList[4502];
    List<Integer> vec2[] = new ArrayList[4502];
    
    boolean bfs(int st, int n){
        for(int i = 0; i < MX; i++){
            ch1[i] = false;
            ch2[i] = false;
        }
        int parCnt = 0;
        int childCnt = 0;
        
        Queue<Integer> q = new ArrayDeque<>();
        
        q.add(st);
        ch1[st] = true;
        
        while(!q.isEmpty()){
            int sz = q.size();
            while(sz-- > 0){
                int cur = q.poll();
                for(int i = 0; i < vec1[cur].size(); i++){
                    int nxt = vec1[cur].get(i);
                    if(ch1[nxt]) continue;
                    q.add(nxt);
                    ch1[nxt] = true;
                    parCnt++;
                }
            }
        }
        
        q.add(st);
        ch2[st] = true;
        while(!q.isEmpty()){
            int sz = q.size();
            while(sz-- > 0){
                int cur = q.poll();
                for(int i = 0; i < vec2[cur].size(); i++){
                    int nxt = vec2[cur].get(i);
                    if(ch2[nxt]) continue;
                    q.add(nxt);
                    ch2[nxt] = true;
                    childCnt++;
                }
            }
        }
        
        return childCnt + parCnt == n - 1;
    }
    public int solution(int n, int[][] results) {
        for(int i = 0; i <= 100; i++){
            vec1[i] = new ArrayList<>();
            vec2[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < results.length; i++){
            int a = results[i][0];
            int b = results[i][1];
            vec1[a].add(b);
            vec2[b].add(a);
        }
        int ret = 0;
        for(int i = 1; i <= 100; i++){
            if(bfs(i, n)) ret++; 
        }

        return ret;
    }
}