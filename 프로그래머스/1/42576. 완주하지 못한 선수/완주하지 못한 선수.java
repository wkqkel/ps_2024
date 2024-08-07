import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new TreeMap<>();
        for(String p : participant){
            if(map.get(p) != null){
                map.put(p, map.get(p) + 1);
            } else {
                map.put(p, 1);
            }
        }
        
        for(String c: completion){
            if(map.get(c) == null) continue;
            map.put(c, map.get(c) - 1);
        }
        
        for(String key : map.keySet()){
           if(map.get(key) > 0) return key;
        }
        
        return "";
    }
}