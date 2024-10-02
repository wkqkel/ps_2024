import java.io.FileInputStream;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int dp[] = new int[N + 10];
		int pre[] = new int[N + 10];

		dp[1] = 0;
		for (int i = 2; i <= N; i++) {
			int mn = dp[i - 1];
			pre[i] = i - 1;
			if (i % 3 == 0 && mn > dp[i / 3]) {
				mn = dp[i / 3];
				pre[i] = i / 3;
			}
			if (i % 2 == 0 && mn > dp[i / 2]) {
				mn = dp[i / 2];
				pre[i] = i / 2;
			}

			dp[i] = mn + 1;
		}
		StringBuilder sb = new StringBuilder();
		int cur = N;
		System.out.println(dp[N]);

		while (true) {
			if (cur == 0)
				break;
			sb.append(cur).append(" ");
			cur = pre[cur];
		}

		System.out.println(sb);
	}
}