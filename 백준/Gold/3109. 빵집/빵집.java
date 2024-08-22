import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int dr[] = new int[]{-1, 0, 1};

    static int n, m;
    static char board[][] = new char[10004][504];
    static int ret = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        for (int i = 0; i < n; i++) {
            if (makePipe(i, 0)) {
                ret++;
            }
        }

        System.out.println(ret);
    }

    static boolean makePipe(int r, int c) {
        if (c == m - 1) {
            return true;
        }
        board[r][c] = '-';

        for (int i = 0; i < 3; i++) {
            int nr = r + dr[i];
            int nc = c + 1;
            if (nr < 0 || nr >= n || nc >= m) {
                continue;
            }
            if (board[nr][nc] != '.') {
                continue;
            }
            if (makePipe(nr, nc)) {
                return true;
            }
        }

        return false;
    }

}