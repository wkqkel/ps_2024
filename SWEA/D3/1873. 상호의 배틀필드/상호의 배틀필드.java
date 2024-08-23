import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	static Map<Character, Integer> TANK_DIR_MAP = new HashMap();
	static Map<Character, Integer> ROTATE_DIR_MAP = new HashMap();
	static char[] DIR_TANK_MAP = new char[] { '^', 'v', '<', '>' };
	static char[][] board = new char[22][22];
	static int dx[] = new int[] { -1, 1, 0, 0 };
	static int dy[] = new int[] { 0, 0, -1, 1 };

	static {
		TANK_DIR_MAP.put('^', 0);
		TANK_DIR_MAP.put('v', 1);
		TANK_DIR_MAP.put('<', 2);
		TANK_DIR_MAP.put('>', 3);
		ROTATE_DIR_MAP.put('U', 0);
		ROTATE_DIR_MAP.put('D', 1);
		ROTATE_DIR_MAP.put('L', 2);
		ROTATE_DIR_MAP.put('R', 3);
	}
	static int R, C;
	static int tankDir;
	static int tankX, tankY;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			// 맵 입력받기
			for (int i = 0; i < R; i++) {
				String str = br.readLine();
				for (int j = 0; j < C; j++) {
					char c = str.charAt(j);
					// 전차의 좌표와 방향을 따로 저장
					if (TANK_DIR_MAP.containsKey(c)) {
						board[i][j] = '.';
						tankX = i;
						tankY = j;
						tankDir = TANK_DIR_MAP.get(c);
					} else {
						board[i][j] = c;
					}
				}
			}

			// 사용자 입력처리
			st = new StringTokenizer(br.readLine());
			String cmds = br.readLine();

			for (char cmd : cmds.toCharArray()) {
				perform(cmd);
//				if (t == 1) {
//					System.out.println("-------");
//					System.out.println(stringMap());
//				}
			}

			sb.append("#").append(t).append(" ").append(stringMap()).append("\n");
		}
		System.out.println(sb);
	}

	static void perform(char cmd) {
		if (cmd == 'S') {
			shoot();
		} else {
			rotate(ROTATE_DIR_MAP.get(cmd));
		}
	}

	static void rotate(int dir) {
		tankDir = dir;
		int nx = tankX + dx[tankDir];
		int ny = tankY + dy[tankDir];
		if (overMap(nx, ny) || board[nx][ny] != '.')
			return;

		board[nx][ny] = '.';
		tankX = nx;
		tankY = ny;
	}

	static boolean overMap(int x, int y) {
		return x < 0 || x >= R || y < 0 || y >= C;
	}

	static void shoot() {
		int curX = tankX;
		int curY = tankY;

		while (true) {
			int nx = curX + dx[tankDir];
			int ny = curY + dy[tankDir];
			if (overMap(nx, ny) || board[nx][ny] == '#')
				break;
			if (board[nx][ny] == '*') {
				board[nx][ny] = '.';
				break;
			}
			curX = nx;
			curY = ny;
		}
	}

	static String stringMap() {
		String str = "";

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (i == tankX && j == tankY) {
					str += DIR_TANK_MAP[tankDir];
				} else {
					str += board[i][j];
				}

			}
			if (i < R - 1)
				str += "\n";
		}

		return str;

	}
}