import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("res/panelsum.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];

		StringTokenizer st2 = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st2.nextToken());
			nums[i] = num;
		}
		
		for(int i = 1;i < N;i++) {
			nums[i] = nums[i] + nums[i-1];
		}
		
		for(int i = 0;i < M;i++) {
			StringTokenizer st3 = new StringTokenizer(bf.readLine());
			int x = Integer.parseInt(st3.nextToken()) - 2;
			int y = Integer.parseInt(st3.nextToken()) - 1;
			
			if(x >= 0 && y >= 0 && x < nums.length && y < nums.length) {
				System.out.println(nums[y] - nums[x]);
			} else {
				System.out.println(nums[y]);
			}
			
		}
	}

}