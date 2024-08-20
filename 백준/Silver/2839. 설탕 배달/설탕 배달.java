import java.util.Scanner;

/**
1. 문제이해
    1. 설탕 n 킬로그램을 5킬로와 3킬로로 가져가려할 때, 적은 걸로 가져가기
2. 전략
    1. 완탐 O(1e6)
        1. n이 최대 5000, 각각 1000개씩 가져간다할 때
        2. 1000개 *1000개해서, 5 * i + 3 * j == n 이면서, i+j가 가장 작을 때 찾음
    2. dp
        1. dp[i]는 i무게일 때 최소 봉지수
        2. 2중반복문 무게 [3,5] w랑 j 돌면서 dp[j] = min(dp[j], dp[j - w] + 1)
 */
public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int dp[] = new int[5002];
		
		for(int i = 0; i <= n; i++) dp[i] = 5000;

		for(int w : new int[] {3,5}) {
			dp[w] = 1;
			for(int j = w; j <= n; j++) {
				dp[j] = Math.min(dp[j], dp[j - w] + 1);
			}
		}
		
		if(dp[n] == 5000) dp[n] = -1;
		System.out.println(dp[n]);
	}
}