import java.io.*;
import java.util.*;

public class Main {

    static long dp[] = new long[104];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= 102; i++) {

            dp[i] = dp[i - 2] + dp[i - 3];
//            System.out.println(dp[i]); // int범위 넘어섬
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }

        System.out.println(sb);
    }


}