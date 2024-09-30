import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int arr[] = new int[N + 2];
		for (int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}
		int dp[][] = new int[N + 2][3];

		dp[1][1] = arr[1];
		dp[1][2] = arr[1];
		dp[2][1] = arr[2];
		dp[2][2] = arr[1] + arr[2];

		for (int i = 3; i <= N; i++) {
			int v = arr[i];

			dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][2]) + v;
			dp[i][2] = dp[i - 1][1] + v;
		}

		System.out.println(Math.max(dp[N][1], dp[N][2]));

	}
}