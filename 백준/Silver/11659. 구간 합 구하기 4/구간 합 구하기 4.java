import java.io.FileInputStream;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int psum[] = new int[N + 10];
		
		for (int i = 1; i <= N; i++) {
			psum[i] = psum[i - 1] + sc.nextInt();
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			sb.append(psum[b] - psum[a - 1]).append("\n");
		}

		System.out.println(sb);
	}
}