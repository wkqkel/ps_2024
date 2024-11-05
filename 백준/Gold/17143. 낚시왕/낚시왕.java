import java.io.*;
import java.util.*;

public class Main {
	static int map[][] = new int[102][102];
	static int nmap[][] = new int[102][102];
	static int R, C, M;
	static Shark sharkPool[] = new Shark[10002];
	static int dx[] = new int[] { 0, -1, 1, 0, 0 };
	static int dy[] = new int[] { 0, 0, 0, 1, -1 };
	static int op[] = new int[] { 0, 2, 1, 4, 3 };

	static class Shark {
		int id;
		int r, c, s, d, z;
		boolean isDead = false;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(r, c, s, d, z);
			shark.id = i;
			map[r][c] = i;
			sharkPool[i] = shark;
		}

		int ret = 0;
		for (int c = 1; c <= C; c++) {
			// 1. 상어먹음
			for (int r = 1; r <= R; r++) {
				if (map[r][c] != 0) {
//					System.out.println("Eat: " + map[r][c]);
					ret += sharkPool[map[r][c]].z;
					sharkPool[map[r][c]].isDead = true;
					map[r][c] = 0;

					break;
				}
			}

			// 2. 상어이동
			nmap = new int[102][102];
			for (int j = 1; j <= M; j++) {
				Shark shark = sharkPool[j];
				if (shark.isDead)
					continue;
				int[] caled = cal(shark.r, shark.c, shark.s, shark.d);

				int nr = caled[0];
				int nc = caled[1];
				int nd = caled[2];
//				System.out.println(shark.id + " " + nr + " " + nc + " " + nd);
				if (nmap[nr][nc] != 0 && sharkPool[nmap[nr][nc]].z > shark.z) {
					shark.isDead = true;
				} else {
					shark.r = nr;
					shark.c = nc;
					shark.d = nd;
					if (nmap[nr][nc] != 0)
						sharkPool[nmap[nr][nc]].isDead = true;
					nmap[nr][nc] = shark.id;

				}
			}
			map = nmap;
//			printMap(map);
		}
		System.out.println(ret);

	}

	static int[] cal(int x, int y, int m, int dir) {

		int cx = x;
		int cy = y;
		int cdir = dir;
		for (int i = 0; i < m; i++) {
			cx += dx[cdir];
			cy += dy[cdir];
			if (cx < 1) {
				cx = 2;
				cdir = op[cdir];
			} else if (cx > R) {
				cx = R - 1;
				cdir = op[cdir];
			} else if (cy < 1) {
				cy = 2;
				cdir = op[cdir];
			} else if (cy > C) {
				cy = C - 1;
				cdir = op[cdir];
			}
		}

		return new int[] { cx, cy, cdir };
	}

	static void printMap(int[][] map) {
		System.out.println("-------------");
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------");
	}

}