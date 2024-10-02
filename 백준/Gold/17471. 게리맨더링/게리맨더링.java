import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int cnt[] = new int[12];
	static ArrayList<Integer> vec[] = new ArrayList[12];
	static boolean tmp[] = new boolean[12];
	static int N = 0;
	static int all = 0;
	static int mn = (int) 1e9;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		for (int i = 0; i < 12; i++) {
			vec[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			cnt[i] = sc.nextInt();
			all += cnt[i];
		}

		for (int i = 0; i < N; i++) {
			int c = sc.nextInt();
			for (int j = 0; j < c; j++) {
				int v = sc.nextInt() - 1;
				vec[i].add(v);
				vec[v].add(i);
			}
		}

		recur(0, 0, 0);

		if (mn == (int) 1e9)
			mn = -1;
		System.out.println(mn);
	}

	static boolean bfs(ArrayList<Integer> team) {
		Queue<Integer> q = new ArrayDeque<>();
		q.clear();
		boolean ch[] = new boolean[12];
		q.add(team.get(0));
		ch[team.get(0)] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int nxt : vec[cur]) {
				if (!team.contains(nxt) || ch[nxt]) {
					continue;
				}
				q.add(nxt);
				ch[nxt] = true;

			}
		}

		for (int m : team) {
			if (!ch[m])
				return false;
		}
		return true;
	}

	static void recur(int cur, int len, int sum) {
		if (cur == N) {
			// 1. 한 팀에 몰리면 X
			if (len == 0 || len == N)
				return;

			ArrayList<Integer> team1 = new ArrayList<>();
			ArrayList<Integer> team2 = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				if (tmp[i])
					team1.add(i);
				else
					team2.add(i);
			}
			if (!bfs(team1) || !bfs(team2)) {
				return;
			}
			mn = Math.min(Math.abs((all - sum) - sum), mn);

			return;
		}

		tmp[cur] = true;
		recur(cur + 1, len + 1, sum + cnt[cur]);
		tmp[cur] = false;
		recur(cur + 1, len, sum);
	}

}