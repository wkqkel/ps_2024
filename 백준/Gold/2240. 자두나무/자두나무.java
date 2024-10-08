import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int arr[] = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int dp[][][] = new int[T + 1][3][W + 1];
        
        // 초기 상태 설정 - 1번위치로 주어짐.
        dp[1][1][0] = arr[1] == 1 ? 1 : 0;
        dp[1][2][1] = arr[1] == 2 ? 1 : 0;
        
        for (int i = 2; i <= T; i++) {
            for (int w = 0; w <= W; w++) {
                int rev = arr[i] == 1 ? 2 : 1;
                // 이동하지 않는 경우
                dp[i][arr[i]][w] = dp[i - 1][arr[i]][w] + 1;
                dp[i][rev][w] = dp[i - 1][rev][w];
                // 이동하는 경우
                if (w > 0) {
                    dp[i][arr[i]][w] = Math.max(dp[i][arr[i]][w], dp[i - 1][rev][w - 1] + 1);
                    dp[i][rev][w] = Math.max(dp[i][rev][w], dp[i - 1][arr[i]][w - 1]);
                }
            }
        }
        int ret = 0;
        for (int w = 0; w <= W; w++) {
            ret = Math.max(ret, Math.max(dp[T][1][w], dp[T][2][w]));
        }
        System.out.println(ret);
    }
}