import java.util.*;

class Solution {
    final int MX = 20012;
    List<Integer>[] vec = new ArrayList[MX];
    boolean ch[] = new boolean[MX];
    
    int bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        
        q.add(1);
        ch[1] = true;
        int mx = -1;
        int cnt = 0;
        int d = 0;
        
        while(!q.isEmpty()){
            int sz = q.size();
         
            while(sz-- > 0){
                int cur = q.poll();
                // System.out.println(cur + " " + d);
                if(d > mx) {
                    cnt = 1;
                    mx = d;
                } else if(d == mx){
                    cnt++;
                }
                for(int nxt : vec[cur]) {
                    if(ch[nxt]) continue;
                    q.add(nxt);
                    ch[nxt] = true;
                }
            }
            d++;
        }
        
        return cnt;
    }

    public int solution(int n, int[][] edge) {
        for(int i = 0; i < MX; i++){
            vec[i] = new ArrayList<>();
        }
        Arrays.fill(ch, false); // 초기화

        for(int i = 0; i < edge.length; i++){ // 수정된 부분
            int a = edge[i][0];
            int b = edge[i][1];
            vec[a].add(b);
            vec[b].add(a);
        }
       
        return bfs();
    }
}
