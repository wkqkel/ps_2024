import java.io.*;
import java.util.*;

// 메모리 145632 실행시간 1480
public class Main {
	static int dx[] = new int[] { -1, 1, 0, 0 };
	static int dy[] = new int[] { 0, 0, -1, 1 };
	static int map[][][] = new int[800][800][2];
	static int C = 400;
	static int N, M, K;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);

		String str1 = sc.next();
		String str2 = sc.next();

		int N = str1.length();
		int M = str2.length();
		int dp[][] = new int[N + 2][M + 2];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				char c1 = str1.charAt(i);
				char c2 = str2.charAt(j);

				if (c1 == c2) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
				} else {
					dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
				}
			}
		}

		System.out.println(dp[N][M]);
	}

}