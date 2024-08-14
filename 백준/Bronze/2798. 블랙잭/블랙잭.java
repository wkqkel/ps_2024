import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int max;
	static int M;
	
	static void DFS(int start, int depth,int sum) {
		if(depth == 3) {
			if(sum <= M) {
				max = Math.max(max, sum);
			}
			//System.out.println(sum);
			return;
		}
		
		for(int i = start;i < arr.length;i++) {
			DFS(i + 1, depth + 1, sum + arr[i]);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/blackjack.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		StringTokenizer st2 = new StringTokenizer(bf.readLine());
		for(int i = 0;i < N;i++) {
			int a = Integer.parseInt(st2.nextToken());
			arr[i] = a; 
		}
		
		int depth = 1;
		for(int i = 0;i < N;i++) {
			DFS(i+1, depth, arr[i]);
		}
		System.out.println(max);
	}
}