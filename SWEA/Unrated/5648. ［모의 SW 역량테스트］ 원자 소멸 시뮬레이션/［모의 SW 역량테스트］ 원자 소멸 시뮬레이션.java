import java.io.*;
import java.util.*;

public class Solution {
	static int dx[] = new int[] { -1, 1, 0, 0 };
	static int dy[] = new int[] { 0, 0, -1, 1 };
	static int map[][] = new int[4002][4002];

	static ArrayList<Atom> atoms = new ArrayList<>();

	static class Atom {
		int x, y, dir, k;
		boolean isDead = false;
		boolean isOver = false;

		public Atom(int x, int y, int dir, int k) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.k = k;
		}

		public void move() {
			this.x += dx[dir];
			this.y += dy[dir];
		}

		@Override
		public String toString() {
			return "Atom [x=" + x + ", y=" + y + ", dir=" + dir + ", k=" + k + "]";
		}
	}

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			atoms.clear();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken()) * -1;
				int dir = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				Atom atom = new Atom((x + 1000) * 2, (y + 1000) * 2, dir, k);
				atoms.add(atom);
			}

			int ret = 0;
			for (int i = 0; i < 4000; i++) {
				move();
				ret += distinct();
			}

			sb.append("#").append(t).append(" ").append(ret).append("\n");
		}

		System.out.println(sb);
	}

	static void move() {
		for (Atom atom : atoms) {
			if (atom.isDead)
				continue;

			atom.move();
			if (atom.x < 0 || atom.x >= 4000 || atom.y < 0 || atom.y >= 4000) {
				atom.isDead = true;
				atom.isOver = true;
			}

		}
	}

	static int distinct() {
		int ret = 0;

		for (Atom atom : atoms) {
			if (atom.isDead)
				continue;
			map[atom.x][atom.y]++;
		}
		for (Atom atom : atoms) {
			if (atom.isDead)
				continue;
			if (map[atom.x][atom.y] >= 2) {
				ret += atom.k;
				atom.isDead = true;
			}
		}

		for (Atom atom : atoms) {
			if (atom.isOver)
				continue;
			map[atom.x][atom.y] = 0;
		}

		return ret;
	}

}