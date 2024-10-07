import java.io.*;
import java.util.*;

public class Main {

    static int arr[][] = new int[502][502];
    static int dp[][] = new int[502][502];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 자신의 위쪽왼쪽, 위쪽 에서부터 누적합 중 큰 값 
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + arr[i][j];
            }
        }

        int mx = 0;
        for (int i = 1; i <= N; i++) {
            mx = Math.max(mx, dp[N][i]);
        }
        
        System.out.println(mx);
    }


}