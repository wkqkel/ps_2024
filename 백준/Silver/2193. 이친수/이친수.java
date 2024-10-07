import java.io.*;
import java.util.*;

public class Main {

    static long dp[][] = new long[1002][2];

    public static void main(String[] args) throws Exception {
     
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
//        int N = 90;

        dp[1][0] = 0;
        dp[1][1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }

        System.out.println(dp[N][0] + dp[N][1]);
    }


}