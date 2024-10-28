import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] num;

	static boolean[] isSelected;
	
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력 받기 시작
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 받기 끝

		// 순열을 위해 필요한 배열 초기화
		isSelected = new boolean[N];
		result = new int[M];
		
		Arrays.sort(num);
		dfs(0);
		System.out.println(sb);
	}
	
	static void dfs(int cnt) {
		
		if (cnt == M) {
			for(int i = 0; i < M; i++) {
				sb.append(result[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		int before = 0;
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) continue;
			if (before == num[i]) continue;

			isSelected[i] = true;
			before = num[i];
			result[cnt] = num[i];
			dfs(cnt+1);
			isSelected[i] = false;
		}
	}
}