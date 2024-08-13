import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public void DFS(int[] arr, boolean[] visited, int depth, int length, int[] nums) {
		if(depth == length) {
			for(int i = 0;i < nums.length;i++) {
				System.out.print(nums[i]+ " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 0;i < arr.length;i++) {
			if(visited[i]) continue; 
				
			visited[i] = true; 
			nums[depth] = arr[i];
			
			DFS(arr, visited, depth+1, length, nums);
			
			visited[i] = false; 
		}
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("res/nmcomb.txt"));
		Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		
		int[] arr = new int[N];
		boolean[] visited = new boolean[N];
		
		for(int i = 0;i < N;i++) {
			arr[i] = i + 1; 
		}
		
		Main nmComb = new Main();
		int[] nums = new int[M];
		
		for(int i = 0;i < N;i++) {
			visited[i] = true;
			nums[0] = arr[i];
			nmComb.DFS(arr,visited,1,M, nums);
			visited[i] = false; 
		}
	}

}