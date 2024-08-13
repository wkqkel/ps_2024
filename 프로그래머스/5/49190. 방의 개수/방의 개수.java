import java.util.*;
import java.io.*;

class V {
    public int x;
    public int y;
    public String id;
    public Set<String> connected = new HashSet<>();
    
    public V(int x, int y){
        this.x = x;
        this.y = y;
        this.id = id(x, y);
    }
    
    static String id(int x, int y){
        return String.format("x: %d, y: %d", x, y);
    }
}

class Solution {
    int dx[] = new int[]{1,1,0,-1,-1,-1,0,1};
    int dy[] = new int[]{0,1,1,1,0,-1,-1,-1};
    
    public int solution(int[] arrows) {
        int ret = 0;
        V cur = new V(0, 0);
        
        Map<String, V> map = new HashMap<>();
        map.put(cur.id, cur);
        
        for(int dir : arrows){
            for(int i = 0; i < 2; i++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                String nxtId = V.id(nx, ny);
                
                if(!map.containsKey(nxtId)){
                    map.put(nxtId, new V(nx, ny));
                } else if(!cur.connected.contains(nxtId)){
                    ret++;
                }
                V nxt = map.get(nxtId);
                nxt.connected.add(cur.id);
                cur.connected.add(nxt.id);
                cur = nxt;
            }
        }

        return ret;
    }
}