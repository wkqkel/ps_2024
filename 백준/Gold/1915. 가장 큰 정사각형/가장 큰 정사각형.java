import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int arr[][] = new int[1004][1004];
		for (int i = 1; i <= N; i++) {
			String[] nums = sc.next().split("");
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(nums[j - 1]);
			}
		}

		int dp[][] = new int[1004][1004]; // 자신을 포함했을 때, 가장 넓은 정사각형 넓이

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (arr[i][j] == 1) {
					dp[i][j] = 1;

					if (arr[i - 1][j - 1] == 1 && arr[i - 1][j] == 1 && arr[i][j - 1] == 1) {
						int mn = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
						dp[i][j] = mn + 1;
					}
				}

			}
		}

//		for (int i = 1; i <= N; i++) {
//
//			for (int j = 1; j <= M; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}

		int mx = 0;
		for (int i = 1; i <= N; i++) {

			for (int j = 1; j <= M; j++) {
				mx = Math.max(dp[i][j], mx);
			}
		}

		System.out.println(mx * mx);
	}

}