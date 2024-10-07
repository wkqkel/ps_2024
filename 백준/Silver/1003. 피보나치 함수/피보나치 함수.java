import java.io.*;
import java.util.*;

public class Main {

    static int dp[] = new int[42];

    public static void main(String[] args) throws Exception {
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            dp = new int[42];
            int ret1 = fibo(N);
            dp = new int[42];
            int ret2 = fibo2(N);

            System.out.println(ret1 + " " + ret2);
        }
    }

    static int fibo(int n) {
        if (dp[n] > 0) {
            return dp[n];
        }
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 0;
        } else {
            return dp[n] = fibo(n - 1) + fibo(n - 2);
        }
    }

    static int fibo2(int n) {
        if (dp[n] > 0) {
            return dp[n];
        }
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return dp[n] = fibo2(n - 1) + fibo2(n - 2);
        }
    }
}