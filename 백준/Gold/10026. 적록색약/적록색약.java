import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Pair {
		public int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		};
	}

	static char[][] map1 = new char[102][102];
	static char[][] map2 = new char[102][102];
	static boolean[][] ch = new boolean[102][102];
	static int dx[] = new int[] { 1, 0, -1, 0 };
	static int dy[] = new int[] { 0, 1, 0, -1 };
	static int n;

	static void bfs(Pair s, char[][] board) {
		Queue<Pair> q = new ArrayDeque<>();
	
		q.add(new Pair(s.x, s.y));
		ch[s.x][s.y] = true;

		while (!q.isEmpty()) {
			int sz = q.size();
			while (sz-- > 0) {
				Pair cur = q.poll();

				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];

					if (nx < 0 || nx >= n || ny < 0 || ny >= n)
						continue;

					if (ch[nx][ny] || board[nx][ny] != board[cur.x][cur.y])
						continue;

					q.add(new Pair(nx, ny));
					ch[nx][ny] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
	
		for (int i = 0; i < n; i++) {
			String str = sc.next();
			for (int j = 0; j < n; j++) {
				map1[i][j] = str.charAt(j);
				map2[i][j] = map1[i][j];
				if (map2[i][j] == 'R')
					map2[i][j] = 'G';
			}
		}

		int ret1 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (ch[i][j]) {
					continue;
				}

				bfs(new Pair(i, j), map1);
				ret1++;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ch[i][j] = false;
			}
		}
		int ret2 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (ch[i][j]) {
					continue;
				}

				bfs(new Pair(i, j), map2);
				ret2++;
			}
		}
		System.out.println(ret1 + " " + ret2);
	}
}