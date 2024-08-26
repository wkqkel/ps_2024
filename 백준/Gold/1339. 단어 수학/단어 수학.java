import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static Map<Character, Integer> alphabetPool = new HashMap<>();

	static int cnt[] = new int[26];
	static int val[] = new int[26];
	static String arr[] = new String[10];
	

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/input2.txt"));
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

	
		for (int i = 0; i < n; i++) {
			String str = sc.next();

			arr[i] = str;
		}

	
	
		for(int i = 0; i < n; i++) {
			String str = arr[i];
			for(int j = 0; j < str.length() ; j++) {
				int delta = (int) Math.pow(10, str.length() - j);
				char c = str.charAt(j);
				cnt[c - 'A'] += delta;
			}
		}
		int newValue = 9;
		for(int i = 0; i < 10; i++) {
			int mx = -1;
			int idx = -1;
			for(int j = 0; j < 26; j++) {
				if(cnt[j] >= mx) {
					mx = cnt[j];
					idx = j;
				}
			}
			if(mx == 0) break;
			cnt[idx] = -1;
			val[idx] = newValue--;
		}
		
		long total = 0;
		
		for(int i = 0; i < n; i++) {
			String str = arr[i];
			String tmp = "";
			for(int j = 0; j < str.length() ; j++) {
				char c = str.charAt(j);
				tmp += val[c - 'A'];
			}
			total += Long.parseLong(tmp);
		}
	
		System.out.println(total);
	}


}