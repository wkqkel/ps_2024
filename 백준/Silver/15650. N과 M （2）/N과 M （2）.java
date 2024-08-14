import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] arr;
	static int[] numbers;
	
	public static void DFS(int start, int depth) {
		if(depth == numbers.length) {
			StringBuilder builder = new StringBuilder();
			for(int i = 0;i < numbers.length;i++) {
				builder.append(numbers[i]).append(" ");
			}
			System.out.println(builder);
			return;
		}
		for(int i = start;i < arr.length;i++) {
			numbers[depth] = arr[i];
			DFS(i+1, depth+1);
		}
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("res/nmcomb.txt"));
		Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt();
		int M = scanner.nextInt();

		arr = new int[N];
		numbers = new int[M];
		
		for(int i = 0;i < N;i++) {
			arr[i] = i + 1; 
		}
		
		int start = 0;
		int depth = 1;
		for(int i = 0;i < N;i++) {
			numbers[0] = arr[i]; 
			DFS(i + 1, depth);
		}
		
		
	}

}