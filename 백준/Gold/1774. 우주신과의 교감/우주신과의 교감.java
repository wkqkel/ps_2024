import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int par[] = new int[1020];
	static ArrayList<Vertex> list = new ArrayList<>();
	static ArrayList<Edge> vec = new ArrayList<>();

	static class Vertex {
		int id;
		double x, y;

		Vertex(int id, double x, double y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}
	}

	static class Edge {
		double d;
		Vertex a, b;

		public Edge() {

		}

		public Edge(Vertex a, Vertex b) {
			this.a = a;
			this.b = b;
			this.d = getDist();
		}

		double getDist() {
			double diffX = Math.abs(a.x - b.x);
			double diffY = Math.abs(a.y - b.y);
			return Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		init();
		list.add(new Vertex(-1, -1, -1));
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			Double x = Double.parseDouble(st.nextToken());
			Double y = Double.parseDouble(st.nextToken());

			list.add(new Vertex(i, x, y));
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			union(a, b);
		}

		for (int i = 1; i <= N; i++) {
			Vertex a = list.get(i);
			for (int j = i + 1; j <= N; j++) {
				Vertex b = list.get(j);
				vec.add(new Edge(a, b));
			}
		}

		Collections.sort(vec, (o1, o2) -> Double.compare(o1.d, o2.d));

		double tot = 0;

		for (Edge edge : vec) {
			if (find(edge.a.id) == find(edge.b.id))
				continue;
			union(edge.a.id, edge.b.id);
			tot += edge.d;
		}

		System.out.println(String.format("%.2f", tot));
	}

	static void init() {
		for (int i = 0; i < 1020; i++) {
			par[i] = i;
		}
	}

	static int find(int a) {
		if (par[a] == a)
			return a;
		return par[a] = find(par[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b)
			return;

		par[b] = a;
	}
}