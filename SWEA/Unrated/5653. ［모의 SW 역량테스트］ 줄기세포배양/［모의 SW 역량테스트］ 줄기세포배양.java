import java.io.*;
import java.util.*;

public class Solution {
	static int dx[] = new int[] { -1, 1, 0, 0 };
	static int dy[] = new int[] { 0, 0, -1, 1 };
	static int map[][][] = new int[800][800][2];
	static int C = 400;
	static int N, M, K;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			for (int i = 0; i < 800; i++) {
				for (int j = 0; j < 800; j++) {
					map[i][j] = new int[] { 0, 0 };
				}
			}

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {

					int p = Integer.parseInt(st.nextToken());

					map[i + C][j + C] = new int[] { p, 0 };
				}
			}

			for (int k = 1; k <= K; k++) {
				for (int x = C - k; x <= C + N + k; x++) {
					for (int y = C - k; y <= C + N + k; y++) {
						// 비어있음

						if (map[x][y][0] == 0)
							continue;

						// 4방향번식
						if (map[x][y][0] + map[x][y][1] + 1 == k) {
							for (int dir = 0; dir < 4; dir++) {
								int nx = x + dx[dir];
								int ny = y + dy[dir];
								if (map[nx][ny][0] == 0) {
									map[nx][ny] = new int[] { map[x][y][0], k };
								} else if (map[nx][ny][1] == k) {
									map[nx][ny][0] = Math.max(map[nx][ny][0], map[x][y][0]);
								}

							}
						}

					}
				}
//				printMap(k);
			}

			int ret = 0;
			for (int x = 0; x < 800; x++) {
				for (int y = 0; y < 800; y++) {
					int[] c = map[x][y];
					if (c[1] == 0)
						continue;

					boolean isDead = K >= map[x][y][0] * 2 + map[x][y][1];
					if (!isDead)
						ret++;

				}
			}
			sb.append("#").append(t).append(" ").append(ret).append("\n");

		}
		System.out.println(sb);
	}

	static void printMap(int k) {
		System.out.println(k + " " + "---------printMap--------");
		for (int x = C - k; x < C + N + k; x++) {
			for (int y = C - k; y < C + N + k; y++) {

				int[] c = map[x][y];
				if (c[0] == 0) {
					System.out.print("0 ");
					continue;
				}
				String status = K >= c[1] + c[0] * 2 ? "D" : "-";
				System.out.printf("(%d, %d, %d, %s) ", map[x][y][0], map[x][y][1], c[1] + c[0] * 2, status);
			}
			System.out.println();
		}
		System.out.println("-------------------------");
	}
}