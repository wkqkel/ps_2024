import java.io.*;
import java.util.*;

public class Main {

    static int MX = 1500002;
    static int t[] = new int[MX];
    static int p[] = new int[MX];

    static int dp[] = new int[MX];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] t = new int[N + 2];
        int[] p = new int[N + 2];
        int[] dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i]는 현재까지 최대 가치
        for (int i = 1; i <= N; i++) {
            int nxt = i + t[i];
            
            if (nxt <= N + 1) {
                dp[nxt] = Math.max(dp[nxt], dp[i] + p[i]);
            }

            if (i + 1 <= N + 1) {
                dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            }
        }

        System.out.println(dp[N + 1]);
    }


}