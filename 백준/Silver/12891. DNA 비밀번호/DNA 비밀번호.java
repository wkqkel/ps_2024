import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
1. 문제이해
    1. 입력
        1. 문자열길이 s, 비밀번호길이 p
        2. dna문자열
        3. 알파벳 최소갯수
    2. dna에서 p길이의 부분문자열을 만들고, 몇개 만들 수 있는지 구하라
2. 전략
    1. 초기값 s = 0, e= 3으로 두고, 
    2. 0~3사이의 글자에 대해 맵에 갯수 저장  
    3. 4~마지막까지 가면서,  s에 대해서는 갯수 빼주고, e에 대해서는 갯수추가해주면서 증가해나감.
    4. 이 때 map이 부분문자열을 만족하면 cnt++
3. 시간복잡도
    1. O(N)
 */
public class Main {

	static Map<Character, Integer> map = new HashMap<>();
	static {
	    map.put('A', 0);
	    map.put('C', 1);
	    map.put('G', 2);
	    map.put('T', 3);
	}
	
	static int cnt[] = new int[4];
	static int ch[] = new int[4];
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < 4; i++) {
			ch[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < m; i++) {
			char c = str.charAt(i);
			if(!map.containsKey(c)) continue;
			cnt[map.get(c)]++;
		}
		
		int ret = 0;
		int s = 0;
		int e = m;

		while(true) {
			if(check()) ret++;
			if(e >= n) break;
			char a = str.charAt(s);
			char b = str.charAt(e);
			if(map.containsKey(a)) cnt[map.get(a)]--;
			if(map.containsKey(b)) cnt[map.get(b)]++;
			s++;
			e++;
		}
	
		System.out.println(ret);
	}
	
	public static boolean check() {
		for(int i = 0; i < 4; i++) {
			if(cnt[i] < ch[i]) return false;
		}
		return true;
	}
	
				
}