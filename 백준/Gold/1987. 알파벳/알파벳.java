import java.io.FileInputStream;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {
	static int R, C;
	static char board[][] = new char[22][22];
	static boolean ch[] = new boolean[26];
	static int dx[] = new int[] { 1, 0, -1, 0 };
	static int dy[] = new int[] { 0, 1, 0, -1 };
	static int mx = 0;

	public static void main(String[] args) throws Exception {
	
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();

		for (int i = 0; i < R; i++) {
			String str = sc.next();
			for (int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		dfs(0, 0, 0);
		System.out.println(mx + 1);
	}

	public static void dfs(int x, int y, int d) {
		if (ch[board[x][y] - 'A'])
			return;

		mx = Math.max(d, mx);
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= R || ny < 0 || ny >= C)
				continue;
			ch[board[x][y] - 'A'] = true;
			dfs(nx, ny, d + 1);
			ch[board[x][y] - 'A'] = false;
		}
	}
}