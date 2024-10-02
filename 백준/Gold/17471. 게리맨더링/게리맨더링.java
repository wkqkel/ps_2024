import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer>[] adjList;
	static int N, res;
	static int[] population;
	static boolean[] v, vertexV;

	static int BFS(int start, int size) {

		int cnt = 1;
		int res = population[start];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		vertexV[start] = true;

		while (!q.isEmpty()) {
			int vertex = q.poll();

			for (int idx : adjList[vertex]) {
				if (!vertexV[idx]) {
					vertexV[idx] = true;
					cnt++;
					res += population[idx];
					q.add(idx);
				}
			}
		}

		if (cnt == size)
			return res;
		return -1;
	}

	static int GetDiff() {
		int start = 0;
		int size = 0;
		for (int i = 1; i <= N; i++) {
			if (v[i]) {
				start = i;
				size++;
				vertexV[i] = false;
			} else
				vertexV[i] = true;
		}

		int val1 = BFS(start, size);
		
		if(val1 == -1) return -1;
		
		start = 0; size = 0;
		for (int i = 1; i <= N; i++) {
			if (!v[i]) {
				start = i;
				size++;
				vertexV[i] = false;
			} else
				vertexV[i] = true;
		}

		int val2 = BFS(start, size);
		
		if(val2 == -1) return -1;
		
		return Math.abs(val1 - val2);
	}

	static void subset(int depth, int cnt) {
		if (depth == N) {
			if(cnt == N || cnt == 0) return;
			
			int val = GetDiff();
			if (val >= 0)
				res = Math.min(res, val);

			return;
		}

		v[depth + 1] = true;
		subset(depth + 1, cnt+1);

		v[depth + 1] = false;
		subset(depth + 1, cnt);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		population = new int[N + 1];
		v = new boolean[N + 1];
		vertexV = new boolean[N + 1];
		adjList = new ArrayList[N + 1];
		res = 1000;

		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int s = 0; s < size; s++) {
				int end = Integer.parseInt(st.nextToken());
				adjList[i].add(end);
				adjList[end].add(i);
			}
		}

		subset(0, 0);

		if (res == 1000)
			res = -1;
		sb.append(res);
		System.out.println(sb);
	}
}