import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> (o2 + o1).compareTo(o1 + o2));

		int max = 0;
		for (int i = 0; i < K; i++) {
			String input = br.readLine();
			pq.offer(input);
			max = Math.max(max, Integer.parseInt(input));
		}

		// 최대값으로 나머지 채우기
		while (pq.size() < N) {
			pq.offer(String.valueOf(max));
		}

		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			sb.append(pq.poll());
		}

		System.out.println(sb);
	}
}