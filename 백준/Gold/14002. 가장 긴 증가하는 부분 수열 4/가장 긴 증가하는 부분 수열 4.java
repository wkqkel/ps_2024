import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int arr[] = new int[1002];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int dp[] = new int[1002];
		int pre[] = new int[1002];

		for (int i = 0; i < N; i++) {
			int mx = 0;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && dp[j] > mx) {
					mx = dp[j];
					pre[i] = j;
				
				}
			}
			dp[i] = mx + 1;
		}

		int cnt = 0;
		int idx = -1;
		for (int i = 0; i < N; i++) {
			if(dp[i] > cnt) {
				cnt = dp[i];
				idx =i;
			}

		}
		List<Integer> path = new ArrayList<>();

		int cur = idx;
		
		for(int i = 0; i < cnt; i++) {
			path.add(arr[cur]);
			cur = pre[cur];
		}

		Collections.reverse(path);

		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		for (int i : path) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

}