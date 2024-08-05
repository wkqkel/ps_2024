import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[][] commands) {
        int n = arr.length;
        int m = commands.length;
        int[] ret = new int[m];
        for(int i = 0; i < m; i++){
            int[] cmd = commands[i];
            int a = cmd[0];
            int b = cmd[1];
            int c = cmd[2];
            a--;
            b--;
            c--;
            
            List<Integer> list = new LinkedList<>();
            for(int j = a; j <= b; j++){
                list.add(arr[j]);
            }
            Collections.sort(list);
            ret[i] = list.get(c);
        }
        return ret;
    }
}