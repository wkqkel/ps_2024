import java.io.*;
import java.util.*;

class Pair {
    public int x;
    public int y;
    
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Solution {
    public int solution(int l, int w, int[] tw) {
        int answer = 0;
        Queue<Pair> passing = new ArrayDeque<>();
        
        int n = tw.length;
        int t = 0;
        int cur = 0;
        int sum = 0;

        while(true){
           if(passing.isEmpty() && cur == n) break;
           if(!passing.isEmpty() && passing.peek().y >= l) {
               Pair out = passing.poll();
               sum -= out.x;
           }
         
           if(cur < n && sum + tw[cur] <= w) {
               Pair in = new Pair(tw[cur], 0);
               sum += in.x;
               passing.add(in);
               cur++;
           }
          
           for(Pair p : passing){
               p.y++;
           }
           t++;
        }
        
        return t;
    }
}