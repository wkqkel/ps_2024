import java.util.*;
import java.io.*;

public class Main {
	static int dx[] = new int[] { 1, 0, -1, 0 };
	static int dy[] = new int[] { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Integer N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
		Integer Q = Integer.parseInt(st.nextToken());

		int board[][] = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int q = 0; q < Q; q++) {

			// N개 나눠서 회전
			int L = Integer.parseInt(st.nextToken());
			int width = (int) Math.pow(2, L);
			int c = N / width;

			for (int i = 0; i < c; i++) {
				for (int j = 0; j < c; j++) {
					rotate(board, width * i, width * (i + 1), width * j, width * (j + 1));
				}
			}

			boolean minus[][] = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int cnt = 0;
					for (int dir = 0; dir < 4; dir++) {
						int nx = i + dx[dir];
						int ny = j + dy[dir];
						if (nx < 0 || nx >= N || ny < 0 || ny >= N)
							continue;
						if (board[nx][ny] > 0)
							cnt++;
					}
					if (cnt < 3)
						minus[i][j] = true;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (minus[i][j] && board[i][j] != 0)
						board[i][j]--;
				}
			}

		}

		// 합구하기
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += board[i][j];
			}
		}
		// 얼음 중 가장 큰 덩어리 차지갯수 bfs
		int mx = 0;

		boolean ch[][] = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (board[i][j] == 0 || ch[i][j])
					continue;
				int cnt = 0;
				Queue<int[]> q = new ArrayDeque<>();

				q.add(new int[] { i, j });
				ch[i][j] = true;

				while (!q.isEmpty()) {
					int[] cur = q.poll();
					cnt++;
					for (int dir = 0; dir < 4; dir++) {
						int nx = cur[0] + dx[dir];
						int ny = cur[1] + dy[dir];
						if (nx < 0 || nx >= N || ny < 0 || ny >= N)
							continue;
						if (board[nx][ny] == 0 || ch[nx][ny])
							continue;

						q.add(new int[] { nx, ny });
						ch[nx][ny] = true;

					}
				}

				mx = Math.max(mx, cnt);
			}
		}

		System.out.println(sum);
		System.out.println(mx);
	}

	static void rotate(int[][] board, int startX, int endX, int startY, int endY) {
	    int size = endX - startX;
	    int[][] tmp = new int[size][size];  // 필요한 크기만큼만 임시 배열 생성
	    
	    // 부분 배열을 임시 배열로 복사
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            tmp[j][size-1-i] = board[startX+i][startY+j];
	        }
	    }
	    
	    // 회전된 결과를 원래 배열에 복사
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            board[startX+i][startY+j] = tmp[i][j];
	        }
	    }
	}
}