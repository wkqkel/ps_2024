import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int durability[] = new int[202];
	static int belts[] = new int[202];

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 2 * N; i++) {
			durability[i] = Integer.parseInt(st.nextToken());
		}

		//
		int t = 0;
		while (true) {
			t++;

			// 1. 로봇과 함께 한 칸회전
			slide(belts);
			slide(durability);
			// 언제든지 도착하면 바로 내림.
			if (belts[N - 1] != 0)
				belts[N - 1] = 0;

			// 2. 가장 먼저 올라간 로봇부터, 오른쪽으로 한칸 이동할 수 있다면 옮김
			// N~(N*2-1)에는 로봇이 존재 X
			// 뒤에서부터가 최신
			for (int i = N - 2; i >= 0; i--) {
				int nxt = i + 1;
				if (belts[i] == 0) // 현재 로봇이 없으면 건너뜀
					continue;
				if (belts[nxt] == 0 && durability[nxt] >= 1) {
					belts[nxt] = belts[i];
					belts[i] = 0;
					durability[nxt]--;
				}
				// 언제든지 도착하면 바로 내림.
				if (belts[N - 1] != 0)
					belts[N - 1] = 0;
			}

			// 3. 올리는 위치에 칸의 내구도가 0이 아니면 로봇을 올림
			if (belts[0] == 0 && durability[0] >= 1) {
				belts[0] = t;
				durability[0]--;
			}
			// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다.
			int zero = 0;
			for (int i = 0; i < 2 * N; i++) {
				if (durability[i] == 0)
					zero++;
			}

			if (zero >= K)
				break;
		}

		System.out.println(t);
	}

	static void slide(int[] ori) {
		int tmp[] = new int[2 * N];
		System.arraycopy(ori, 0, tmp, 1, 2 * N - 1);

		tmp[0] = ori[(2 * N) - 1];

		System.arraycopy(tmp, 0, ori, 0, 2 * N);
	}

}