import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int AIR = 0, CHEESE = 1, HOLE = 2;

	static int R, C;
	static int board[][] = new int[102][102];
	static int prev[][] = new int[102][102];
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
				prev[r][c] = board[r][c];
			}
		}
		int time = 0;
		while (true) {

			mark();

			if (getCheeseCnt(board) == 0) {
				System.out.println(time + "\n" + getCheeseCnt(prev));
				break;
			}
			time++;
			melt();
		}
	}

	public static void mark() {
		boolean ch[][] = new boolean[102][102];
		Queue<Pair> q = new ArrayDeque<>();

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (board[r][c] == CHEESE) {
					continue;
				}

				board[r][c] = HOLE;
			}
		}

		q.add(new Pair(0, 0));
		ch[0][0] = true;

		while (!q.isEmpty()) {
			int sz = q.size();

			while (sz-- > 0) {
				Pair cur = q.poll();
				board[cur.x][cur.y] = AIR;

				for (int dir = 0; dir < 4; dir++) {
					int nx = cur.x + dx[dir];
					int ny = cur.y + dy[dir];

					if (nx < 0 || nx >= R || ny < 0 || ny >= C)
						continue;
					if (ch[nx][ny] || board[nx][ny] == CHEESE)
						continue;

					q.add(new Pair(nx, ny));
					ch[nx][ny] = true;
				}
			}
		}
	}

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

	static void melt() {
		int tmp[][] = new int[102][102];

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				tmp[r][c] = board[r][c];
			}
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (board[r][c] != AIR) {
					continue;
				}

				for (int dir = 0; dir < 4; dir++) {
					int nx = r + dx[dir];
					int ny = c + dy[dir];

					if (nx < 0 || nx >= R || ny < 0 || ny >= C)
						continue;
					tmp[nx][ny] = AIR;
				}
			}
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				prev[r][c] = board[r][c];
				board[r][c] = tmp[r][c];
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