import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int dp[][] = new int[1002][10];
        int mod = 10007;

        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        // dp[i][j]는 i번째자리의 끝이 j일때
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 9; k++) {
                    if (k >= j) {
                        dp[i][j] += dp[i - 1][k] % mod;
                    }
                }
            }
        }

        int ret = 0;
        for (int i = 0; i <= 9; i++) {
            ret += dp[N][i] % mod;
        }

        System.out.println(ret % mod);
    }


}