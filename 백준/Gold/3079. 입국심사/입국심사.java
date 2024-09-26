import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static Long N, M;
	static long time[] = new long[100020];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());

		for (int i = 0; i < N; i++) {
			time[i] = Long.parseLong(br.readLine());
		}

		long s = 1;
		long e = (long) (1e9) * M;
		long ret = e;
		while (s <= e) {
			long mid = (s + e) / 2;

			long cnt = 0;
			for (int i = 0; i < N; i++) {
				cnt += (mid / time[i]);
				if (cnt >= M)
					break;
			}

			if (cnt < M) {
				s = mid + 1;
			} else {
				ret = Math.min(ret, mid);
				e = mid - 1;
			}
		}

		System.out.println(ret);
	}
}