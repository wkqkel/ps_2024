import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int arr[] = new int[10002];
		for (int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}

		int dp[][] = new int[10002][3];

		for (int i = 1; i <= N; i++) {
			dp[i][0] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]);
			dp[i][1] = dp[i - 1][0] + arr[i];
			dp[i][2] = dp[i - 1][1] + arr[i];
		}

		int ret = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				ret = Math.max(ret, dp[i][j]);
			}
		}
		System.out.println(ret);
	}

}