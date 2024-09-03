import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int par[] = new int[100020];
	static ArrayList<int[]> vec = new ArrayList<>();

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				Integer.parseInt(st.nextToken());
				Integer.parseInt(st.nextToken());
			}
			sb.append(V - 1).append("\n");
		}

		System.out.println(sb);
	}

}