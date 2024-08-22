import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 문제이해 1. n * n의 방 2. 4방향에 대해 현재 방보다 크면 이동 가능 3. 최대 이동할 수 있을 때 방+ 갯수 출력 1.
 * 여러 개 라면 방번호가 작은 것 4. n 최대 10^3, 테케 27, 제한시간 1초 2. 전략 1. 완탐 1. n*n반복문 돌면서
 * O(n^2) 2. bfs 돌려서 갯수 카운트 1. 4방향에 대해, +1이면 큐에 넣음 2. 갯수가 같거나 크면 갯수 갱신 1. 이때방번호가
 * 작으면 갯수갱신 2. 시간복잡도? 1. 2가 n^2이 아니라 1~N^2을 다 더한값인 듯 2. = (1+n^2) * n^2 / 2
 */
class Pair {
	int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Triple implements Comparable<Triple> {
	int num;
	Pair pos;

	public Triple(int num, Pair pos) {
		this.num = num;
		this.pos = pos;
	}

	@Override
	public int compareTo(Triple o) {
		return num - o.num;

	}
}

public class Solution {
	static int[] dx = new int[] { 1, 0, -1, 0 };
	static int[] dy = new int[] { 0, 1, 0, -1 };
	static int n;
	static int map[][] = new int[1002][1002];

	static boolean ch_num[] = new boolean[1000002];
	static boolean ch[][] = new boolean[1002][1002];
	static int mx_cnt = -1;
	static int idx = -1;

	public static void bfs(Pair s) {
		Queue<Pair> q = new ArrayDeque<>();

		q.add(s);

		int cnt = 0;

		while (!q.isEmpty()) {
			Pair cur = q.poll();
			int num = map[cur.x][cur.y];

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n)
					continue;
				if ((ch_num[map[nx][ny]] && cnt <= mx_cnt) || map[nx][ny] != map[cur.x][cur.y] + 1)
					continue;

				q.add(new Pair(nx, ny));

				ch_num[num] = true;
			}
			cnt++;
		}

		if (mx_cnt < cnt) {
			mx_cnt = cnt;
			idx = map[s.x][s.y];
		}

	}

	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		PriorityQueue<Triple> pq = new PriorityQueue<>();

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			for (int i = 0; i < 1000002; i++) {
				ch_num[i] = false;
			}
			mx_cnt = -1;
			idx = -1;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					pq.add(new Triple(map[i][j], new Pair(i, j)));

				}
			}

			while (!pq.isEmpty()) {
				Triple p = pq.poll();
				if (ch_num[p.num])
					continue;

				bfs(p.pos);
			}

			sb.append("#").append(t).append(" ").append(idx).append(" ").append(mx_cnt).append("\n");
		}

		System.out.println(sb);
	}
}