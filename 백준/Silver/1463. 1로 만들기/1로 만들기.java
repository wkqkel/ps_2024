import java.util.Scanner;

public class Main {
	static int mn = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();

//		recur(x, cnt);
//		System.out.println(mn);
		// dp[x]는 x일 때 최소연산횟수
		int dp[] = new int[1000002];
		
		for (int i = 2; i <= x; i++) {
			int mn = dp[i - 1];
			if (i % 3 == 0) {
				mn = Math.min(mn, dp[i / 3]);
			}
			if (i % 2 == 0) {
				mn = Math.min(mn, dp[i / 2]);
			}
			dp[i] = mn + 1;
		}
		
		System.out.println(dp[x]);
	}

	static void recur(int x, int cnt) {
		if (x == 1) {
			mn = Math.min(mn, cnt);
			return;
		}
		if (x % 3 == 0)
			recur(x / 3, cnt + 1);
		if (x % 2 == 0)
			recur(x / 2, cnt + 1);
		recur(x - 1, cnt + 1);
	}
}