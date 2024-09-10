import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int map[][] = new int[24][24];
	static int likes[][] = new int[402][4];
	static int SCORES[] = new int[] { 0, 1, 10, 100, 1000 };
	static int ret = 0;
	static ArrayList<Integer> order = new ArrayList<>();
	static int N;
	static int dr[] = new int[] { 1, 0, -1, 0 };
	static int dc[] = new int[] { 0, -1, 0, 1 };
	static PriorityQueue<Seat> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력받기
		N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				likes[v][j] = Integer.parseInt(st.nextToken());
			}
			order.add(v);
		}

		// 순서 돌기

		for (int cur : order) {
			pq.clear();
			// 맵 돌면서
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] != 0)
						continue;

					pq.add(new Seat(r, c, getLikeCnt(r, c, cur), getEmptyCnt(r, c)));

				}
			}

			map[pq.peek().r][pq.peek().c] = cur;
		}

		// 점수세기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				ret += SCORES[getLikeCnt(r, c, map[r][c])];
			}
		}

		System.out.println(ret);

	}

	static int getLikeCnt(int r, int c, int cur) {
		int cnt = 0;
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;

			for (int like : likes[cur]) {
				if (map[nr][nc] == like) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	static int getEmptyCnt(int r, int c) {
		int cnt = 0;
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			if (map[nr][nc] == 0)
				cnt++;
		}
		return cnt;
	}

	static class Seat implements Comparable<Seat> {
		int r, c, like, empty;

		Seat(int r, int c, int like, int empty) {
			this.r = r;
			this.c = c;
			this.like = like;
			this.empty = empty;
		}

		@Override
		public int compareTo(Seat o) {
			if (like != o.like) {
				return o.like - like;
			}
			if (empty != o.empty) {
				return o.empty - empty;
			}
			if (r != o.r) {
				return r - o.r;
			}
			return c - o.c;
		}
	}

}