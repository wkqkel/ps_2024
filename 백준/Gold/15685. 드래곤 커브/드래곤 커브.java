import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		boolean map[][] = new boolean[102][102];
		int dx[] = new int[] { 0, -1, 0, 1 };
		int dy[] = new int[] { 1, 0, -1, 0 };

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// 1. 반복문을 돌면서 커브를 그린다.
		for (int i = 0; i < N; i++) {
			// 시작 정보를 입력받는다. (이 때 -> 가 x좌표가 증가하는 방향임을 주의)
			int y = sc.nextInt();
			int x = sc.nextInt();
			int d = sc.nextInt();
			int g = sc.nextInt();

			int curves[] = new int[1030];
			int len = 0;
			curves[len++] = d;
			// 세대를 돌면서,
			for (int j = 1; j <= g; j++) {
				// 이전까지의 방향 정보의 역순으로 90도 회전시킨 커브 정보를 저장
				for (int k = len - 1; k >= 0; k--) {
					curves[len++] = (curves[k] + 1) % 4;
				}
			}

			// 시작점을 기준으로 커브를 표시한다.
			map[x][y] = true;
			for (int j = 0; j < len; j++) {
				x += dx[curves[j]];
				y += dy[curves[j]];
				if (x < 0 || x >= 101 || y < 0 || y >= 101)
					continue;
				map[x][y] = true;
			}
		}
		
		// 2. 맵을 돌면서, 사각형으로 그려진 부분을 찾는다.
		int ret = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
					ret++;
				}
			}
		}
		
		System.out.println(ret);
	}
}