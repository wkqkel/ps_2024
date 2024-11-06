import java.io.*;
import java.util.*;

public class Main {
	static int N, M, T;
	static int x, d, k;

	static int arr[][] = new int[52][52];
	static boolean mark[][] = new boolean[52][52];

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int t = 0; t < T; t++) { // 1e3

			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			// 1. 원판들 회전
			for (int i = x; i <= N; i += x) { // 50
				for (int j = 0; j < k; j++) { // 50
					rotate(arr[i], d); // 50
				}
			}

			// 수가 남아있으면
			boolean flag = false;
			find: for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (arr[i][j] != 0) {
						flag = true;
						break find;
					}
				}
			}

			if (!flag)
				break;

			// 인접한 수 모두 찾기
			mark = new boolean[52][52];
			boolean find = false;

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (arr[i][j] == 0)
						continue;
					if (findAround(i, j))
						find = true;
				}
			}


			// 있다면 모두지움
			if (find) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= M; j++) {
						if (mark[i][j])
							arr[i][j] = 0;
					}
				}

			} else {
				// 없다면 평균을 구하고,
				int sum = 0;
				int cnt = 0;
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= M; j++) {
						sum += arr[i][j];
						if (arr[i][j] != 0)
							cnt++;
					}
				}
				double avg = (double) sum / cnt;
		
				// 평균보다 큰수는 1을 빼고, 작은수는 1을 더함
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= M; j++) {
						if (arr[i][j] == 0)
							continue;
						if (arr[i][j] > avg) {
							arr[i][j]--;
						} else if (arr[i][j] < avg) {
							arr[i][j]++;
						}
					}
				}
			}



		}

		// 원판에 적힌 수의 합 출력
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sum += arr[i][j];
			}
		}

		System.out.println(sum);
	}

	static void rotate(int arr[], int dir) {

		if (dir == 0) { // 시계
			int tmp = arr[M];
			for (int i = M - 1; i >= 1; i--) {

				arr[i + 1] = arr[i];

			}
			arr[1] = tmp;
		} else {
			int tmp = arr[1];
			for (int i = 1; i < M; i++) {
				arr[i] = arr[i + 1];
			}
			arr[M] = tmp;
		}

	}

	static boolean findAround(int i, int j) {
		int cur = arr[i][j];
		boolean find = false;
		if (j == 1) {
			if (arr[i][2] == cur || arr[i][M] == cur)
				find = true;
		} else if (j == M) {
			if (arr[i][M - 1] == cur || arr[i][1] == cur)
				find = true;
		} else {
			if (arr[i][j - 1] == cur || arr[i][j + 1] == cur)
				find = true;
		}

		if (i == 1) {
			if (arr[2][j] == cur)
				find = true;
		} else if (i == N) {
			if (arr[N - 1][j] == cur)
				find = true;
		} else {
			if (arr[i - 1][j] == cur || arr[i + 1][j] == cur)
				find = true;
		}

		if (find) {
			mark[i][j] = true;
		}

		return find;
	}

	static void printArr() {
		System.out.println("------------");
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("------------");
	}
}