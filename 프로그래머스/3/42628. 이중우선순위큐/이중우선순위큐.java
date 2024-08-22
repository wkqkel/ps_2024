import java.util.*;
import java.io.*;

class Solution {
    static PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> minQ = new PriorityQueue<>();
    public int[] solution(String[] ops) {
        StringTokenizer st;
        for(String op : ops){
            st = new StringTokenizer(op);
            String cmd = st.nextToken();
            int val = Integer.parseInt(st.nextToken());
 
            if("I".equals(cmd)){
                maxQ.add(val);
                minQ.add(val);
            }
            else if(val == 1){
                minQ.remove(maxQ.poll());
            }
            else {
                maxQ.remove(minQ.poll());
            }
        }
        
        if(maxQ.isEmpty()) maxQ.add(0);
        if(minQ.isEmpty()) minQ.add(0);
        
        return new int[]{maxQ.poll(), minQ.poll()};
    }
}