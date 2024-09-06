import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String ori = br.readLine();
			TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
			char[] chars = ori.toCharArray();
			for (int r = 0; r <= N / 4; r++) {
				chars = slide(chars);
				for (int i = 0; i < chars.length; i += N / 4) {
					StringBuilder str = new StringBuilder();
					for (int j = 0; j < N / 4; j++) {
						str.append(chars[i + j]);
					}
					set.add(Integer.parseInt(str.toString(), 16));
				}
			}
			int ret = (int) set.toArray()[K - 1];
			sb.append("#").append(t).append(" ").append(ret).append("\n");
		}
		System.out.println(sb);
	}

	static char[] slide(char[] chars) {
		int len = chars.length;
		char[] tmp = new char[len];
		System.arraycopy(chars, 0, tmp, 1, len - 1);
		tmp[0] = chars[len - 1];
		return tmp;
	}
}
