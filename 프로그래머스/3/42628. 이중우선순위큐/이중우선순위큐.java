import java.util.*;
import java.io.*;

class DoublePq {
    PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minPq = new PriorityQueue<>();
    Map<Integer, Integer> cnt = new HashMap<>();
    int sz = 0;
    
    public void push(int v){
        maxPq.add(v);
        minPq.add(v);
        cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        sz++;
    }
    
    public void popMin(){
        if(sz == 0) return;
        while(true){
            int v = minPq.poll();
            if(cnt.get(v) > 0){
                cnt.put(v, cnt.get(v) - 1);
                break;
            }
        }
        sz--;
    }
    
    public void popMax(){
        if(sz == 0) return;
        while(true){
            int v = maxPq.poll();
            if(cnt.get(v) > 0){
                cnt.put(v, cnt.get(v) - 1);
                break;
            } 
        }
        sz--;
    }
    
    public int getMin(){
        int ret = 0;
        if(sz == 0) return ret;
        while(true){
            ret = minPq.peek();
            if(cnt.get(ret) > 0){
                return ret;
            } else {
                minPq.poll();
            }
        }
    }
    
    public int getMax(){
        int ret = 0;
        if(sz == 0) return ret;
        while(true){
            ret = maxPq.peek();
            if(cnt.get(ret) > 0){
                return ret;
            } else {
                maxPq.poll();
            }
        }
    }
    
    public int[] getResult(){
        return new int[]{getMax(), getMin()};
    }
    

}

class Solution {
    
    public int[] solution(String[] ops) {
        int[] answer = {};
        DoublePq pq = new DoublePq();
        
        for(String op : ops){
           StringTokenizer st = new StringTokenizer(op);
           String cmd = st.nextToken();
           int v = Integer.valueOf(st.nextToken());
    
           if(cmd.equals("I")){
               pq.push(v);
           } else if (v == -1){
               pq.popMin();
           } else {
               pq.popMax();
           }
        }
        
        return pq.getResult();
    }
}