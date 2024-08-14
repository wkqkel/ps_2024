import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	static int[] arr;
	static int[] numbers;
	
	public static void DFS(int start, int depth) {
		if(depth == numbers.length) {
			int total = 0;
			for(int i = 0;i < numbers.length;i++) {
				total += numbers[i];
			}
			if(total == 100) {
				for(int i = 0;i < numbers.length;i++) {
					System.out.println(numbers[i]);
				}	
			}
			return;
		}
		for(int i = start; i < arr.length;i++) {
			numbers[depth] = arr[i];
			DFS(i+1, depth+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("res/snowwhite.txt"));
		Scanner scanner = new Scanner(System.in);
		
		arr = new int[9];
		for(int i = 0;i < 9;i++) {
			arr[i] = scanner.nextInt(); 
		}
		
		int depth = 1;
		numbers = new int[7];
		boolean[] visited = new boolean[9];
		for(int i = 0;i < 9;i++) {
			numbers[0] = arr[i];
			DFS(i+1, depth);
		}
	}

}