import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int map[][] = new int[20][20];
    static int DIRS[][] = new int[][]{{0, 2}, {1, 2}, {0, 1, 2}};
    static int dx[] = new int[]{0, 1, 1};
    static int dy[] = new int[]{1, 0, 1};
    static int N;
    static int ret = 0;
    static int CHECK[][] = new int[][]{{0}, {1}, {0, 1, 2}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0, 0, 0);
        System.out.println(ret);

    }

    static void dfs(int cur, int x, int y, int dir) {
        int ex = x + dx[dir];
        int ey = y + dy[dir];
        if (ex == N - 1 && ey == N - 1) {
            ret++;
            return;
        }
        if (cur == 2 * N) {
            return;
        }
        for (int nd : DIRS[dir]) {
            boolean can = true;

            for (int i : CHECK[nd]) {
                int nex = ex + dx[i];
                int ney = ey + dy[i];

                if (nex < 0 || nex >= N || ney < 0 || ney >= N || map[nex][ney] == 1) {
                    can = false;
                    break;
                }
            }
            if (!can) {
                continue;
            }
            dfs(cur + 1, ex, ey, nd);
        }
    }
}