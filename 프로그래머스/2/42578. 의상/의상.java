import java.util.*;
import java.io.*;

class Solution {
    Map<String, List<String>> map = new HashMap<>();
 
    public int solution(String[][] clothes) {
        int answer = 0;
        
        for(String[] c : clothes){
            if(map.get(c[1]) != null){
                List<String> list = map.get(c[1]);
                list.add(c[0]); 
                map.put(c[1], list);
            } else {
                List<String> list = new LinkedList<String>();
                list.add(c[0]);
                map.put(c[1], list);
            }
        }
        
        int ret = 1;
        
        for(String key : map.keySet()){
            int n = map.get(key).size();
            ret *= (n + 1); // 각 종류 + 선택x
        }
        ret--; // 아무것도 선택x제외
       
        return ret;
    }
}