import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int arr[] = new int[N + 4];
		for (int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}

		int M = sc.nextInt();

		boolean dp[][] = new boolean[N + 4][N + 4];

		for (int i = N; i >= 1; i--) {
			for (int j = i; j <= N; j++) {
				if (j - i == 0) {
					dp[i][j] = true;
				} else if (j - i == 1) {
					dp[i][j] = arr[i] == arr[j];
				} else {
					dp[i][j] = arr[i] == arr[j] && dp[i + 1][j - 1];
				}

			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			sb.append(dp[a][b] ? 1 : 0).append("\n");
		}
		
		System.out.println(sb);
	}

}