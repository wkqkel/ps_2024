import java.io.*;
import java.util.*;

public class Main {

    static long dp[] = new long[1002];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int mod = 10007;

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 3;

        // 1*2를 채우면 n-1, 2*1을채우면 밑은 2*1로 채울수밖에없으니 n-2, 2*2를 채우면 n-2가 남게 되고 다 더한 경우의 수
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] % mod + dp[i - 2] * 2 % mod;
        }

        System.out.println(dp[N] % mod);
    }


}