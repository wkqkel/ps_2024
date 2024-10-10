import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int dx[] = new int[] { 1, 0, -1, 0 };
	static int dy[] = new int[] { 0, -1, 0, 1 };
	static int ret = (int) 1e9;
	static List<int[]> changed = new ArrayList<>();
	static char map[][] = new char[52][52];

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] s = new int[] { 0, 0 };
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S') {
					s[0] = i;
					s[1] = j;
				}
			}
		}

		bfs(s);

		System.out.println("KAKTUS");

	}

	static void bfs(int[] s) {
		Queue<int[]> q = new ArrayDeque<>();
		char[][] nmap = new char[N][M];
		boolean[][] ch = new boolean[N][M];
		copyMap(nmap, map);

//		print(nmap);

		q.add(s);
		ch[s[0]][s[1]] = true;
		int ret = 0;
		while (!q.isEmpty()) {
			int sz = q.size();
			// 물확산
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					for (int dir = 0; dir < 4; dir++) {
						if (map[i][j] != '*')
							continue;
						int nx = i + dx[dir];
						int ny = j + dy[dir];
						if (overMap(nx, ny))
							continue;
						if (map[nx][ny] == 'X' || map[nx][ny] == 'D')
							continue;
						nmap[nx][ny] = '*';
					}
				}
			}
			while (sz-- > 0) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				if (map[x][y] == 'D') {
					System.out.println(ret);
					System.exit(0);
				}
				// 고슴도치 이동
				for (int dir = 0; dir < 4; dir++) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];

					if (overMap(nx, ny))
						continue;
					if (ch[nx][ny])
						continue;
					if (nmap[nx][ny] == 'X' || nmap[nx][ny] == '*')
						continue;
		
					q.add(new int[] { nx, ny });
					ch[nx][ny] = true;
				}
			}
			ret++;
			copyMap(map, nmap);
		}

	}

	static boolean overMap(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}

	static void copyMap(char[][] map1, char[][] map2) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map1[i][j] = map2[i][j];
			}
		}
	}
}