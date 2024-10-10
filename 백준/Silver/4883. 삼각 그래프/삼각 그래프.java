import java.io.*;
import java.util.*;

public class Main {

    static int arr[][] = new int[100020][3];
    static int dp[][] = new int[100020][3];
    static int N;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(t++).append(".").append(" ").append(solve()).append("\n");
        }
        System.out.println(sb);
    }

    static int solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        // dp[0][0] = arr[0][0]; // 0,1에서 출발가능하므로 값을 높여서 출발안하게함.
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][1] + arr[0][2];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                int mn = dp[i - 1][j];

                if (j == 0) {
                    mn = Math.min(mn, dp[i - 1][j + 1]);
                } else if (j == 1) {
                    mn = Math.min(mn, dp[i - 1][j - 1]);
                    mn = Math.min(mn, dp[i - 1][j + 1]);
                    mn = Math.min(mn, dp[i][j - 1]);
                } else {
                    mn = Math.min(mn, dp[i - 1][j - 1]);
                    mn = Math.min(mn, dp[i][j - 1]);
                }

                dp[i][j] = mn + arr[i][j];
            }
        }

        return dp[N - 1][1];
    }

}