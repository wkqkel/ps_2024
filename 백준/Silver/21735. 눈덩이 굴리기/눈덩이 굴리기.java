import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] arr;
	
	static int max = 0;
	
	static void snow(int cnt, int now, int sum) {
		
		if (cnt == M || now >= N) {
			max = Math.max(max, sum);
			return;
		}
		
		if (now + 1 <= N) {
			snow(cnt+1, now+1, sum + arr[now+1]);
		}
		if (now + 2 <= N) {
			snow(cnt+1, now+2, (sum/2) + arr[now+2]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력받기시작
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 입력받기끝 
		
		snow(0,0,1);
		
		System.out.println(max);
		
	}

}