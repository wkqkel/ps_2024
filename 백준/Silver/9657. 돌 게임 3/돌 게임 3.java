import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		boolean dp[] = new boolean[1002];

		dp[1] = true;
		dp[2] = false;
		dp[3] = true;
		dp[4] = true;

		for (int i = 5; i <= n; i++) {
			dp[i] = !(dp[i - 1] && dp[i - 3] && dp[i - 4]);
		}

		if (dp[n]) {
			System.out.println("SK");
		} else {
			System.out.println("CY");
		}
	}

}