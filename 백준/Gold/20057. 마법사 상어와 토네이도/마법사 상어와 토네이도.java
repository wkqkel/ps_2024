import java.io.*;
import java.util.*;

public class Main {
	static int map[][] = new int[600][600];

	static int dx[] = new int[] { 0, 1, 0, -1 };
	static int dy[] = new int[] { -1, 0, 1, 0 };
	static int N;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i + 2][j + 2] = Integer.parseInt(st.nextToken());
			}
		}

		// 토테이도
		int dir = 0;
		int dist = 1;
		int x = (N / 2) + 2, y = (N / 2) + 2;

		outer: for (int k = 0; k < N; k++) {
			for (int i = 0; i < 2; i++) {

				for (int j = 0; j < dist; j++) {

					if (x == 2 && y == 2)
						break outer;

					x += dx[dir];
					y += dy[dir];

					// 먼지흩날리기
					send(x, y, dir);
				}
				dir = (dir + 1) % 4;
				if (i == 1)
					dist++;
			}
		}

		int ret = 0;
		for (int i = 0; i < N + 4; i++) {
			for (int j = 0; j < N + 4; j++) {
	
				if (i < 2 || j < 2 || i > N + 1 || j > N + 1) {
					ret += map[i][j];
				}
			}
	
		}

		System.out.println(ret);
	}

	static void send(int x, int y, int dir) {

		int tmp[][] = new int[5][5];
		int sum = 0;
		sum += tmp[0][2] = (int) (map[x][y] * 0.02);
		sum += tmp[1][1] = (int) (map[x][y] * 0.10);
		sum += tmp[1][2] = (int) (map[x][y] * 0.07);
		sum += tmp[1][3] = (int) (map[x][y] * 0.01);

		sum += tmp[2][0] = (int) (map[x][y] * 0.05);

		sum += tmp[3][1] = (int) (map[x][y] * 0.10);
		sum += tmp[3][2] = (int) (map[x][y] * 0.07);
		sum += tmp[3][3] = (int) (map[x][y] * 0.01);
		sum += tmp[4][2] = (int) (map[x][y] * 0.02);

		tmp[2][1] = map[x][y] - sum;
		tmp[2][2] = -map[x][y];

		for (int i = 0; i < dir; i++) {
			rotate(tmp, 5);
		}

		// 더해줌.
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[x - 2 + i][y - 2 + j] += tmp[i][j];
			}
		}
	}

	static void rotate(int[][] arr, int N) {
		int tmp[][] = new int[5][5];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmp[N - j - 1][i] = arr[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = tmp[i][j];
			}
		}

	}
}