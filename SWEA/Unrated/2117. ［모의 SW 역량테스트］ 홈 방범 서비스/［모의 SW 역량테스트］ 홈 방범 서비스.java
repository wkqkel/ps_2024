import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static BufferedReader br;
	static StringTokenizer st;
	static int map[][] = new int[22][22];

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int mx = 1;
			// k 정하기
			for (int k = 1; k <= N + 1; k++) {
				int keepCost = (k * k) + (k - 1) * (k - 1);

				// k를 반지름으로 하는 맵 돌기
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						// 운영범위 영역 안의 집 갯수 찾기
						int cnt = getInAreaCnt(r, c, k - 1);
						int benefit = M * cnt - keepCost;
						if (benefit < 0)
							continue;
						mx = Math.max(cnt, mx);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(mx).append("\n");
		}
		System.out.println(sb);
	}

	static int getInAreaCnt(int r, int c, int k) {
		int cnt = 0;
		for (int nr = r - k; nr <= r + k; nr++) {
			for (int nc = c - k; nc <= c + k; nc++) {
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue; // 맵에 벗어나면
				if (Math.abs(nr - r) + Math.abs(nc - c) > k)
					continue; // 마름모꼴 내에 없으면
				if (map[nr][nc] != 1)
					continue; // 집이 아니라면

				cnt++;
			}
		}
		return cnt;
	}

}