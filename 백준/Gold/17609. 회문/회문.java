import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(valid(br.readLine().toCharArray())).append("\n");
		}
		System.out.println(sb);
	}

	static public int valid(char[] str) {
		int s = 0;
		int e = str.length - 1;
		int cnt1 = 0;
		// 왼쪽
		while (s <= e) {
			if (str[s] != str[e]) {
				s++;
				cnt1++;
			} else {
				s++;
				e--;
			}
		}

		s = 0;
		e = str.length - 1;
		int cnt2 = 0;

		while (s <= e) {
			if (str[s] != str[e]) {
				e--;
				cnt2++;
			} else {
				s++;
				e--;
			}
		}

		return Math.min(2, Math.min(cnt1, cnt2));
	}
}