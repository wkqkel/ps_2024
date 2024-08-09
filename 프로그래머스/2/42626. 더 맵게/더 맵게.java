import java.util.*;

class Solution {
    public int solution(int[] arr, int k) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i : arr){
            pq.add(i);
        }
        
        while(pq.size() > 1 && pq.peek() < k){
            int mn1 = pq.peek();
            pq.remove();
            int mn2 = pq.peek();
            pq.remove();
            int mix = mn1 + mn2 * 2;
            pq.add(mix);
            answer++;
        }
        
        if(pq.peek() < k) return -1;
        return answer;
    }
}