import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int L, C;
	static char[] arr;
	static char[] tmp = new char[22];
	static String MOUM = "aeiou";
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		L = sc.nextInt();
		C = sc.nextInt();
		arr = new char[C];

		for (int i = 0; i < C; i++) {
			arr[i] = sc.next().charAt(0);
		}
		Arrays.sort(arr);

		recur(0, 0, 0, 0);
		System.out.println(sb);
	}

	// cnt1은 모음카운트, cnt2는 자음카운트 => 1, 2개이상
	static void recur(int cur, int st, int cnt1, int cnt2) {
		if (cur == L) {
			if (cnt1 < 1 || cnt2 < 2)
				return;

			for (int i = 0; i < L; i++)
				sb.append(tmp[i]);

			sb.append("\n");
			return;
		}
		for (int i = st; i < C; i++) {
			tmp[cur] = arr[i];

			recur(cur + 1, //
					i + 1, //
					isMoum(tmp[cur]) ? cnt1 + 1 : cnt1, //
					!isMoum(tmp[cur]) ? cnt2 + 1 : cnt2);
		}
	}

	static boolean isMoum(char c) {
		return MOUM.contains(String.valueOf(c));
	}
}