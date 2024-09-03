import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, C;
	static int tmp[] = new int[10]; // 선택한 벌꿀
	static int map[][] = new int[12][12];
	static int mx;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			mx = 0;
			solution();
			sb.append("#").append(t).append(" ").append(mx).append("\n");
		}
		System.out.println(sb);
	}

	static void solution() {
		// 1. 벌통 담기 경우의 수
		ArrayList<int[]> person = getOnePerson();
		for (int i = 0; i < person.size(); i++) {
			for (int j = 0; j < person.size(); j++) {
				// 겹치는지 확인
				int[] person1 = person.get(i);
				int[] person2 = person.get(j);

				// 한 사람의 y구간이 다른 사람의 시작점 또는 끝점을 포함하는지 체크
				boolean isCollapseY = false;
				for (int c = person2[1]; c <= person2[2]; c++) {
					if (c == person1[1] || c == person1[2]) {
						isCollapseY = true;
					}
				}
				boolean isCollaspeX = person1[0] == person2[0];
				if (isCollaspeX && isCollapseY)
					continue;
//				System.out.println(Arrays.toString(person1) + "  || " + Arrays.toString(person2));
				int honey1 = getHoney(0, person1[0], person1[1], person1[2]);
				int honey2 = getHoney(0, person2[0], person2[1], person2[2]);
//				System.out.println(honey1);
				mx = Math.max(mx, honey1 + honey2);
			}
		}
	}

	static ArrayList<int[]> getOnePerson() {
		ArrayList<int[]> ret = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				// x, y시작점, y끝점
				ret.add(new int[] { i, j, j + M - 1 });
			}
		}
		return ret;
	}

	// m개 부분집합 중 c를 넘지않는 최대값 2^5
	// 최댓값일 때 선택된 꿀들에 대한 제곱의 합 구하기
	static int getHoney(int cur, int r, int sc, int ec) {
		int ret = 0;
		if (cur == M) {
			int sum = 0;
			int benifit = 0;

			for (int v : tmp) {
				sum += v;
				benifit += v * v;
			}

			if (sum > C)
				return 0;

			return benifit;

		}
		tmp[cur] = map[r][sc + cur];
		ret = Math.max(ret, getHoney(cur + 1, r, sc, ec));
		tmp[cur] = 0;
		ret = Math.max(ret, getHoney(cur + 1, r, sc, ec));

		return ret;
	}
}
