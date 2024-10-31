import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력 받기 시작
		int N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int[] tang = new int[N];
		for (int i = 0; i < N; i++) {
			tang[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 받기 끝

		if (N == 1) {
			System.out.println(1);
			return;
		}

		int ans = 2;

		int start = 0;
		int end = 0;
		int idx = 0;

		// 과일 종류 ( 1 ~ 9 )
		int[] cur = new int[10];
		
		while (end < N) {
			
			cur[tang[end++]]++;
			
			while( count(cur) > 2) {
				cur[tang[start++]]--;
			}
			
			ans = Math.max(ans, end-start);
		}
		
		System.out.println(ans);

	}
	
	// 없는 숫자를 세는 함수
	static int count(int[] arr) {
		
		int cnt = 0;
		for (int i : arr) {
			if (i != 0) {
				cnt++;
			}
		}
		
		return cnt;
	}

}