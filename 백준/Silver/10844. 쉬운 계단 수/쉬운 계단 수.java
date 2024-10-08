import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long mod = (int) 1e9;
		// n자리 끝자리수
		long dp[][] = new long[102][10];
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				if (j == 0) {
					dp[i][j] = dp[i - 1][1];
				} else if (j == 9) {
					dp[i][j] = dp[i - 1][8];
				} else {
					dp[i][j] = dp[i - 1][j - 1] % mod + dp[i - 1][j + 1] % mod;
				}
			}
		}

		long ret = 0;
		for (int i = 0; i <= 9; i++) {
			ret += dp[N][i];
		}
		System.out.println(ret % mod);
	}

}