import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        
        int[] plums = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            plums[i] = Integer.parseInt(br.readLine());
        }
        
        int[][][] dp = new int[T + 1][3][W + 1];
        
        // 초기 상태 설정
        if (plums[1] == 1) {
            dp[1][1][0] = 1;
        } else {
            dp[1][2][1] = 1;
        }
        
        for (int t = 2; t <= T; t++) {
            int currentTree = plums[t];
            
            // 이동하지 않는 경우
            for (int w = 0; w <= W; w++) {
                dp[t][1][w] = dp[t-1][1][w];
                dp[t][2][w] = dp[t-1][2][w];
                
                if (currentTree == 1) {
                    dp[t][1][w]++;
                } else {
                    dp[t][2][w]++;
                }
            }
            
            // 이동하는 경우
            for (int w = 1; w <= W; w++) {
                dp[t][1][w] = Math.max(dp[t][1][w], dp[t-1][2][w-1] + (currentTree == 1 ? 1 : 0));
                dp[t][2][w] = Math.max(dp[t][2][w], dp[t-1][1][w-1] + (currentTree == 2 ? 1 : 0));
            }
        }
        
        int maxPlums = 0;
        for (int w = 0; w <= W; w++) {
            maxPlums = Math.max(maxPlums, Math.max(dp[T][1][w], dp[T][2][w]));
        }
        
        System.out.println(maxPlums);
    }
}