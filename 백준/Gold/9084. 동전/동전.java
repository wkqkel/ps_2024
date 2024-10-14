import java.io.*;
import java.util.*;

// 메모리 145632 실행시간 1480
public class Main {
	static int coins[] = new int[22];
	static int dp[] = new int[10002];

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			for (int i = 0; i < N; i++) {
				coins[i] = sc.nextInt();
			}

			int M = sc.nextInt();

			Arrays.fill(dp, 0);
			dp[0] = 1;
			for (int i = 0; i < N; i++) {
				int coin = coins[i];
				for (int j = coin; j <= M; j++) {
					dp[j] += dp[j - coin];
				}
			}

			System.out.println(dp[M]);
		}

	}

}