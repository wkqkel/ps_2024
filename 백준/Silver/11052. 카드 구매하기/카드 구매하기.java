import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int arr[] = new int[1002];

		for (int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}

		// dp[i]는 i개 카드를 구매했을 때 최대 가격
		int dp[] = new int[1002];

		for (int i = 1; i <= N; i++) {
			dp[i] = arr[i];

			for (int j = 1; j <= i; j++) {
				dp[i] = Math.max(dp[i], dp[i - j] + dp[j]);
			}
		}

		System.out.println(dp[N]);
	}

}