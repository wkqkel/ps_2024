import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		char board[][] = new char[n][m];
		boolean ch[][] = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			String str = sc.next();
			for (int j = 0; j < m; j++) {
				board[i][j] = str.charAt(j);
			}
		}

		Queue<Integer[]> q = new ArrayDeque<>();
		int dx[] = new int[] { 1, 0, -1, 0 };
		int dy[] = new int[] { 0, 1, 0, -1 };

		q.add(new Integer[] { 0, 0 });
		ch[0][0] = true;
		
		int d = 1;
		while (!q.isEmpty()) {
			int sz = q.size();
		
			while (sz-- > 0) {
				Integer[] cur = q.poll();

				if (cur[0] == n - 1 && cur[1] == m - 1) {
					System.out.println(d);
					return;
				}

				for (int dir = 0; dir < 4; dir++) {
					int nx = cur[0] + dx[dir];
					int ny = cur[1] + dy[dir];

					if (nx < 0 || nx >= n || ny < 0 || ny >= m)
						continue;
					if (ch[nx][ny] || board[nx][ny] != '1')
						continue;

					q.add(new Integer[] { nx, ny });
					ch[nx][ny] = true;
				}
			}
			d++;
		}

	}

}