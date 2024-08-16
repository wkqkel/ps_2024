import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
1. 문제이해
    1. 재료가 주어지고, 신맛과 쓴맛이 주어진다.
    2. 사용하는 재료들의 신맛의 곱과 쓴맛의 합 의 차가 적은 걸 조합을 구해라
    3. 자료형 고민.
        1. 요리의 신맛쓴맛이 10억 → int범위내
2. 전략
    1. 재귀의 파라미터로, 현재 재료순번, 총 신맛, 총쓴맛 넘김
    2. 기저조건
        1. n이 됐을 때, 신맛과 쓴맛의 차가 적은 걸 저장
    3. 유도파트
        1. 아래에는 신맛은 현재재료 곱해서 넘긴거, 쓴맛 더해서 넘긴거
        2. 그대로 넘긴거 
3. 시간복잡도
    1.  O(2^10)
 */
public class Main {
	static int[][] tastes = new int[20][2];
	static int n;
	static int mn = Integer.MAX_VALUE;

	
	static void recur(int cur, int mul, int sum, int cnt) {
		if(cur == n) {
			if(cnt == 0) return;
			int diff = Math.abs(mul - sum);
			
			mn = Math.min(diff, mn);
			return;
		}
		
		recur(cur + 1, mul * tastes[cur][0], sum + tastes[cur][1], cnt + 1);
		recur(cur + 1, mul, sum, cnt);
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			tastes[i][0] = Integer.parseInt(st.nextToken());
			tastes[i][1] = Integer.parseInt(st.nextToken());
		}
		
		recur(0, 1, 0, 0);
		
		System.out.println(mn);
		
	}
}