import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		List<String> list = new ArrayList<>();
		String[] strs = str.split("::");

		if(strs.length == 0) {
			System.out.println("0000:0000:0000:0000:0000:0000:0000:0000");
			return;
		}
		String a = strs[0];
		String[] aa = a.split(":");
		

		fill(aa);
		for (String s : aa) {
			list.add(s);
		}

		if (strs.length > 1) {
			String[] bb = strs[1].split(":");
			fill(bb);
			int sz = 8 - (aa.length + bb.length);
	
			for (int i = 0; i < sz ; i++) {
				list.add("0000");
			}
			for (String s : bb) {
				list.add(s);
			}
		} else if(str.contains("::")) {
			int sz = 8 - (aa.length);
			for (int i = 0; i < sz ; i++) {
				list.add("0000");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 8; i++) {
			sb.append(list.get(i));
			if(i != 7) {
				sb.append(":");
			}
		}
		
		System.out.println(sb);
	}

	static void fill(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].length() != 4) {
				arr[i] = "0".repeat(4 - arr[i].length()) + arr[i];
			}
		}
	}

}