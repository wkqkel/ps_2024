import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int AIR = 0, CHEESE = 1;

	static int R, C;
	static int board[][] = new int[102][102];

	static int dx[] = new int[] { 1, 0, -1, 0 };
	static int dy[] = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());

			}
		}

		int time = 0;
		int cheeseCnt = getCheeseCnt(board);
		while (true) {
			if (getCheeseCnt(board) == 0) {
				break;
			} else {
				cheeseCnt = getCheeseCnt(board);
			}

			time++;
			melt();
		}
		System.out.println(time + "\n" + cheeseCnt);
	}

	/**
	 * @param map
	 * @return 주어진 이차원배열의 치즈 갯수 반환
	 */
	static int getCheeseCnt(int[][] map) {
		int cheeseCnt = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == CHEESE)
					cheeseCnt++;
			}
		}
		return cheeseCnt;
	}

	/**
	 * 맵돌면서 공기와 닿은 치즈를 녹임.
	 */
	static void melt() {
		boolean ch[][] = new boolean[102][102];
		Queue<Pair> q = new ArrayDeque<>();

		q.add(new Pair(0, 0));
		ch[0][0] = true;

		while (!q.isEmpty()) {
			int sz = q.size();

			while (sz-- > 0) {
				Pair cur = q.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nx = cur.x + dx[dir];
					int ny = cur.y + dy[dir];

					if (nx < 0 || nx >= R || ny < 0 || ny >= C)
						continue;
					if (ch[nx][ny])
						continue;
					ch[nx][ny] = true;
					if (board[nx][ny] == CHEESE) {
						board[nx][ny] = AIR;

					} else {
						q.add(new Pair(nx, ny));
					}

				}
			}
		}
	}

	static void printMap() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.println();
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