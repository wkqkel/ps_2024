import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int arr[] = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr);

		int ret[] = new int[] { Integer.MAX_VALUE, -1, -1 };

		for (int i = 0; i < N; i++) {
			int st = i + 1;
			int en = N - 1;

			while (st <= en) {
				int mid = (st + en) / 2;
				int sum = arr[mid] + arr[i];
				int abs = Math.abs(sum);

				if (ret[0] > abs) {
					ret[0] = abs;
					ret[1] = arr[i];
					ret[2] = arr[mid];
				}
				if (sum < 0) {
					st = mid + 1;
				} else {
					en = mid - 1;
				}
			}
		}
		if (ret[1] > ret[2]) {
			int tmp = ret[1];
			ret[1] = ret[2];
			ret[2] = tmp;
		}

		System.out.println(ret[1] + " " + ret[2]);
	}
}