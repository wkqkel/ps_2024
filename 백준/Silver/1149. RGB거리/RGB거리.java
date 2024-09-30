import java.io.FileInputStream;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int arr[][] = new int[N + 2][3];
		int dp[][] = new int[N + 2][3];

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		for (int i = 1; i <= N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
		}

		System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]));

	}
}