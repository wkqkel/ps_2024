import java.util.*;
import java.io.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stk = new Stack<>();
        
        for(char c : s.toCharArray()){
            if(c == '('){
                stk.add('(');
            } else {
                if(stk.empty() || stk.peek() != '(') return false;
                stk.pop();
            }
        }
        
        return stk.isEmpty();
    }
}