import java.io.FileInputStream;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		long MOD = 10007;
		long dp[] = new long[N + 2];

		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= N; i++) {
			dp[i] = dp[i - 1] % MOD + dp[i - 2] % MOD;
		}

		System.out.println(dp[N] % MOD);

	}
}