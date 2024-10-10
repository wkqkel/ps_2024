import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = sc.nextInt();
        }
        long[] dp = new long[K + 1];

        for (int coin : coins) {
            if (coin > K) {
                continue;
            }

            dp[coin] += 1;
            for (int i = coin; i <= K; i++) {
                dp[i] += dp[i - coin];
            }
        }

        System.out.println(dp[K]);
    }
}