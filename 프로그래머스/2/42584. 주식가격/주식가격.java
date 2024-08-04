import java.util.*;
import java.io.*;

class Pair {
    int x;
    int y;
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class Solution {
    public int[] solution(int[] arr) {
       
        int n = arr.length;
        Stack<Pair> stk = new Stack<>();
        int[] ret = new int[n];
        for(int i = n - 1; i >= 0; i--){
            while(!stk.isEmpty() && stk.peek().x >= arr[i]) stk.pop();
            if(!stk.isEmpty() && stk.peek().x < arr[i]) {
                ret[i] = stk.peek().y - i;
            } else {
                ret[i] = n - i - 1;
                
            }
            stk.push(new Pair(arr[i], i));
        }
        
        return ret;
    }
}