import java.io.*;
import java.util.*;

class Solution {
    
    public String solution(int[] nums) {
        List<String> list = new ArrayList<>();
        
        int sum = 0;
        for(int num: nums){
            list.add(String.valueOf(num));
            sum += num;
        }
        if(sum == 0){
            return "0";
        }
        
        Collections.sort(list, (o1, o2)-> {
            return (o2 + o1).compareTo(o1 + o2);
        });
      
        StringBuilder sb = new StringBuilder();
        
        for(String i : list){
            sb.append(i);
        }
        

        return sb.toString();
    }
}