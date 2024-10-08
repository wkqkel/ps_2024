import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        boolean ch[] = new boolean[42];
        for (int i = 0; i < M; i++) {
            ch[sc.nextInt()] = true;
        }

        long dp[][] = new long[42][2];

        // dp[i][j]
        // i번쨰자리를 왼쪽이랑 안바꾼거는 0, 바꾼건 1
        dp[1][0] = 1;
        dp[1][1] = 0;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            if (!ch[i] && !ch[i - 1]) {
                dp[i][1] = dp[i - 1][0];
            }
        }

        System.out.println(dp[N][0] + dp[N][1]);
    }


}