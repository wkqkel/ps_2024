import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Point {
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		int y;
		int x;
	}

	static int b[][] = new int[9][9];
	static List<Point> zeros = new ArrayList<>();

	static boolean rowCheck(int x, int val) {
		for (int i = 0; i < 9; i++) {
			if (b[i][x] == val)
				return false;
		}
		return true;
	}

	static boolean colCheck(int y, int val) {
		for (int i = 0; i < 9; i++) {
			if (b[y][i] == val)
				return false;
		}
		return true;
	}

	static boolean blockCheck(int y, int x, int val) {
		int sy = (y / 3) * 3;
		int sx = (x / 3) * 3;

		for (int r = sy; r < sy + 3; r++) {
			for (int c = sx; c < sx + 3; c++) {
				if(r == y && c == x) continue;
				if(b[r][c] == val)
					return false;
			}
		}
		return true;
	}

	static boolean flag = false;
	static void FillBoard(int start) {
		
		if(start >= zeros.size()) {
			flag = true;
			StringBuilder sb = new StringBuilder();
			
			for (int y = 0; y < 9; y++) {
				for (int x = 0; x < 9; x++) {
					sb.append(b[y][x]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			return;
		}
		
		int r = zeros.get(start).y;
		int c = zeros.get(start).x;
		
		for (int num = 1; num <= 9; num++) {
			
			if (rowCheck(c, num) && colCheck(r, num) && blockCheck(r, c, num)) {
				b[r][c] = num;
				FillBoard(start+1);
				if(flag) return;
				b[r][c] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 인풋
		for (int y = 0; y < 9; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int x = 0; x < 9; x++) {
				b[y][x] = str.charAt(x) - '0';
				if (b[y][x] == 0)
					zeros.add(new Point(y, x));
			}
		}
		
		FillBoard(0);
	}

}