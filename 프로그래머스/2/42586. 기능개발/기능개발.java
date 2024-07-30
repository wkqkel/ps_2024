import java.util.*;

class Solution {
    public List<Integer> solution(int[] p, int[] s) {
        int[] answer = {};
        int n = p.length;
        int d[] = new int[n+2];
        for(int i = 0; i < n; i++){
            int x = (int) Math.ceil((double)(100 - p[i]) / s[i]);
            d[i+1] = x;
        }
        List<Integer> ret = new ArrayList<>();
        int cnt = 0;
        d[n+1] = 100000;
        for(int i = 1; i <= n+1; i++){
            if(d[i-1] >= d[i]) {
                d[i] = d[i-1];
            }
            else if((i != 1 && d[i] != d[i-1]) || i == n){
                ret.add(cnt);
                cnt = 0;
            }  
            cnt++;
        }
        
        return ret;
    }
}