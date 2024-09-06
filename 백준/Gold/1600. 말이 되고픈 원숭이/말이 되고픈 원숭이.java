import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int dx[] = new int[] { 1, 0, -1, 0, -1, -2, -2, -1, 1, 2, 2, 1 };
	static int dy[] = new int[] { 0, 1, 0, -1, -2, -1, 1, 2, -2, -1, 1, 2 };
	static int map[][] = new int[202][202];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		boolean ch[][][] = new boolean[202][202][32];
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 0, 0, K });

		int dep = 0;
		while (!q.isEmpty()) {
			int sz = q.size();
		
			while (sz-- > 0) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				int r = cur[2];
				int dirs = r == 0 ? 4 : 12;

				if (x == R - 1 && y == C - 1) {
					System.out.println(dep);
					return;
				}
				for (int dir = 0; dir < dirs; dir++) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					int nr = dir < 4 ? r : r - 1;

					if (nx < 0 || nx >= R || ny < 0 || ny >= C)
						continue;
					if (map[nx][ny] == 1)
						continue;
					if (ch[nx][ny][nr])
						continue;
					q.add(new int[] { nx, ny, nr });
					ch[nx][ny][nr] = true;
				}
			}
			dep++;
		}
		
		System.out.println(-1);
	}
}