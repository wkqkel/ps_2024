import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Map<Integer, Integer> map = new TreeMap<>();
        for(int i : nums){
            if(map.get(i) != null) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            };
        }
        
        return Math.min(map.size(), nums.length / 2);
    }
}