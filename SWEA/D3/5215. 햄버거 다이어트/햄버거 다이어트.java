import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int test = 1; test <= T; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int tastes[] = new int[N];
			int kals[] = new int[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				tastes[i] = Integer.parseInt(st.nextToken());
				kals[i] = Integer.parseInt(st.nextToken());
			}
			
			int mx = 0;
			for(int i = 0; i < 1 << N; i++) {
				int total_k = 0;
				int total_t = 0;
				for(int j = 0; j < N; j++) {
					if((i & 1 << j) != 0) {
						total_k += kals[j];
						total_t += tastes[j];
					}
				}
				if(total_k > L) continue;
				mx = Math.max(total_t, mx);
			}
			sb.append("#").append(test).append(" ").append(mx).append("\n");
		}
		
		System.out.println(sb);
	}
	
}