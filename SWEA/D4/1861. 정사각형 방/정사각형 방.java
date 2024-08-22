import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static final int[] dx = {1, 0, -1, 0};
    public static final int[] dy = {0, 1, 0, -1};
    static int n;
    static int board[][] = new int[1002][1002];
    static int dp[][] = new int[1002][1002];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = 0;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dfs(i, j);
                }
            }

            int mx = -1;
            int idx = 1000000;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][j] > mx) {
                        mx = dp[i][j];
                        idx = board[i][j];
                    } else if (dp[i][j] == mx) {
                        idx = Math.min(idx, board[i][j]);
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(idx).append(" ").append(mx).append("\n");
        }
        System.out.println(sb);
    }

    static int dfs(int x, int y) {
        if (dp[x][y] > 0) {
            return dp[x][y];
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                continue;
            }
            if (board[nx][ny] != board[x][y] + 1) {
                continue;
            }

            return dp[x][y] = dfs(nx, ny) + 1;
        }

        return dp[x][y] = 1;
    }
}