/**
##문제이해
- n개의 좌표 (r,c)
- 로봇마다 m개로 이루어진 운송경로
- 동시에 출발하여 4방향으로만 이동 가능
    - 다음포인트로 항상 최단경로로 이동
    - r이 c보다 먼저 이동 
- 마지막 포인트에 도착하면, 운송을 마치고,물류센터를 벗어남
- **위험상황**
    - 이동 중 같은 좌표에 2대 이상 모이는 상황
    - 각 시간마다 위험상황인 횟수 세기 
- 모든 운송을 마칠 때까지 위험상황 세기
## 전략
- 최단거리 이동 (x1,y1)에서 (x2,y2)로 간다면 
    - x먼저 이동 (x1 - x2)가 음수면 차이만큼 +, 양수면 차이만큼 -
    - y도 마찬가지
- 시간마다 어떻게?
    - 삼차원 bfs? 시간마다 방문한게 같으면 체크 => 불가능
    - 루트를 미리 시간별로 이동을 다 구해놓자

*/
import java.util.*;

class Solution {
    
    public int solution(int[][] points, int[][] routes) {
        int n = routes.length;
        int t = 0;
  
        List<int[]>[] paths = new ArrayList[n];
        for(int i = 0; i < n; i++){
            paths[i] = getRoutePerTime(points, routes[i]);
        }
        int ret = 0;
        
        while(true){
            boolean isEnd = true;
            Map<Integer, Integer> ch = new HashMap<>();

            for(int i = 0; i < n; i++){
                List<int[]> path = paths[i];
                if(path.size() <= t) continue;
                int[] cur = path.get(t);
 
                isEnd = false;
                int idx = cur[0] * 102 + cur[1];
                if(ch.get(idx) != null){
                    ch.put(idx, ch.get(idx) + 1);
                } else {
                    ch.put(idx, 1);
                }
            }
            for(Integer key : ch.keySet()){
                int val = ch.get(key);
                if(val < 2) continue;
              
                ret += 1;
            }
            if(isEnd) break;
            t++;
        }
        return ret;
    }
    
    public List<int[]> getRoutePerTime(int[][] points, int[] route){
        List<int[]> list = new ArrayList<>();
        
        for(int i = 0; i < route.length - 1; i++){
            int[] cur = points[route[i] - 1];
            int[] nxt = points[route[i+1] - 1];
            int cx = cur[0];
            int cy = cur[1];
            int gx = nxt[0];
            int gy = nxt[1];
            while(true){
                if(cx == gx && cy == gy){
                    break;
                }
                list.add(new int[]{cx,cy});
              
                if(cx != gx){
                    int diff = cx - gx;
                    cx = diff < 0 ? cx + 1 : cx - 1;
                } else {
                    int diff = cy - gy;
                    cy = diff < 0 ? cy + 1 : cy - 1;
                }
            }
        }
        int[] last = points[route[route.length -1] - 1];
        list.add(last);
        return list;
    }
}