import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		// dp[i]는 정수 i를 1,2,3의 합으로 나타낸 경우의 수

		long dp[] = new long[1000002];
		long mod = 1000000009;

		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i <= 1000000; i++) {
			dp[i] = (dp[i - 1] % mod + dp[i - 2] % mod + dp[i - 3] % mod) % mod;
		}
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			int N = sc.nextInt();
			sb.append(dp[N]).append("\n");
		}

		System.out.println(sb);
	}

}