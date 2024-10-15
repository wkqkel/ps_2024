import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		int coins[] = new int[n];

		for (int i = 0; i < n; i++) {
			coins[i] = sc.nextInt();
		}

		int dp[] = new int[k + 1];

		Arrays.fill(dp, (int) 1e9);
		dp[0] = 0;
		for (int coin : coins) {
			for (int i = coin; i <= k; i++) {
				dp[i] = Math.min(dp[i], dp[i - coin] + 1);
			}
//			System.out.println(Arrays.toString(dp));
		}

		if (dp[k] == (int) 1e9) {
			System.out.println(-1);
			return;
		}
		System.out.println(dp[k]);
	}

}