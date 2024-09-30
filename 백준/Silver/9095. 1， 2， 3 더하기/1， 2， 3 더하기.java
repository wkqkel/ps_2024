import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			int N = sc.nextInt();
			int dp[] = new int[N + 10];

			dp[0] = 0;
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;

			for (int i = 4; i <= N; i++) {
				dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
			}
			sb.append(dp[N]).append("\n");
	
		}
		System.out.println(sb);
	}
}