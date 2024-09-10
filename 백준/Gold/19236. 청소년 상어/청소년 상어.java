import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 1. 상어 먹음 2. 물고기 이동 3. 상어이동
 * 
 * 상태 변화 많음. dfs(cmap, cfish, sharkX, sharkY, sum) 복사 map = cmap, fish = cfish
 * 
 * // 상어먹음
 * 
 */
public class Main {

	static int ret = 0;
	static int dx[] = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = new int[] { 0, -1, -1, -1, 0, 1, 1, 1 };

	static class Fish {
		int x, y, dir;

		Fish(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int map[][] = new int[4][4];
		Fish fish[] = new Fish[17];
		// 입력받기 + 초기화
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int n = sc.nextInt();
				int d = sc.nextInt() - 1;
				map[i][j] = n;
				fish[n] = new Fish(i, j, d);
			}
		}

		dfs(map, fish, 0, 0, 0);
		System.out.println(ret);
	}

	static void dfs(int[][] cmap, Fish[] cfish, int sharkX, int sharkY, int sum) {

		// 0. 배열 복사
		int[][] map = new int[4][4];
		Fish[] fish = new Fish[17];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++)
				map[i][j] = cmap[i][j];
		}

		for (int i = 1; i < 17; i++) {
			fish[i] = new Fish(cfish[i].x, cfish[i].y, cfish[i].dir);
		}

		// 1. 상어 먹음
		int eat = map[sharkX][sharkY];
		int sharkDir = fish[eat].dir;
		fish[eat] = new Fish(-1, -1, -1);
		map[sharkX][sharkY] = -1;
		sum += eat;
		ret = Math.max(ret, sum);

		// 2. 물고기 이동
		for (int i = 1; i <= 16; i++) {
			if (fish[i].x == -1)
				continue;
			int cx = fish[i].x;
			int cy = fish[i].y;
			int cd = fish[i].dir;

			int nx = cx + dx[cd];
			int ny = cy + dy[cd];
			int nd = cd;

			while (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || (sharkX == nx && sharkY == ny)) {
				nd = (nd + 1) % 8;
				nx = cx + dx[nd];
				ny = cy + dy[nd];
			}

			int target = map[nx][ny];
			map[nx][ny] = i;
			map[cx][cy] = target;
			fish[i] = new Fish(nx, ny, nd);
			if (target != -1) {
				fish[target] = new Fish(cx, cy, fish[target].dir);
			}
		}

		// 3. 상어 이동
		for (int i = 1; i <= 3; i++) {
			int nx = sharkX + dx[sharkDir] * i;
			int ny = sharkY + dy[sharkDir] * i;

			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[nx][ny] == -1)
				continue;

			dfs(map, fish, nx, ny, sum);
		}
	}
}