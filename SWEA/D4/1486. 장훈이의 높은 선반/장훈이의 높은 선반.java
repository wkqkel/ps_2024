import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int arr[] = new int[n];
			for(int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}
			
			int mn = 1000000;
			for(int i = 0; i < 1 << n; i++) {
				int sum = 0;
				for(int j = 0; j < n; j++) {
					if((i & (1 << j)) != 0) {
						// 선택했으면,
						sum += arr[j];
					}
				}
				if(sum >= m) mn = Math.min(sum - m, mn);
			}
			sb.append("#").append(t).append(" ").append(mn).append("\n");
		}
		System.out.println(sb);
	}
}