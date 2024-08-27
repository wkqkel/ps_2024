import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int N;
	static int board[][] = new int[102][102];
	static boolean ch[][] = new boolean[102][102];
	static int dx[] = new int[] { 1, 0, -1, 0 };
	static int dy[] = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();

			// 맵입력받기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					board[r][c] = sc.nextInt();
				}
			}

			// day
			int mx = 0;
			for (int day = 0; day <= 100; day++) {
				// 초기화
				int cnt = 0;
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						ch[r][c] = false;
					}
				}
				// board
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (ch[r][c] || board[r][c] <= day)
							continue;

						bfs(new Pair(r, c), day);
						cnt++;
					}
				}
				
				// max값갱신
				mx = Math.max(mx, cnt);
			}

			sb.append("#").append(t).append(" ").append(mx).append("\n");
		}
		System.out.println(sb);
	}

	public static void bfs(Pair s, int day) {
		Queue<Pair> q = new ArrayDeque<>();

		q.add(s);
		ch[s.x][s.y] = true;

		while (!q.isEmpty()) {
			int sz = q.size();
			while (sz-- > 0) {
				Pair cur = q.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nx = cur.x + dx[dir];
					int ny = cur.y + dy[dir];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if (ch[nx][ny] || board[nx][ny] <= day)
						continue;
					ch[nx][ny] = true;
					q.add(new Pair(nx, ny));
				}
			}
		}
	}

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}