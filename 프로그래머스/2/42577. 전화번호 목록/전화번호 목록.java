import java.util.*;

import java.util.*;
import java.io.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();
        Arrays.sort(phone_book, (s1, s2)-> s1.length() - s2.length()); 
        for(String s: phone_book){
            for(int i = 1; i <= s.length(); i++){
                String v = s.substring(0, i);
                if(set.contains(v)) return false;
            }
          
            set.add(s);
        }
        
        return true;
    }
}