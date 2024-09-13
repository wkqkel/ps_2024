import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<Integer> arr = new ArrayList<>();
    static int tmp[] = new int[12];
    static int map[][] = new int[52][52];
    static int dx[] = new int[]{1, 0, -1, 0};
    static int dy[] = new int[]{0, -1, 0, 1};
    static int mn = (int) 1e9;
    static int zero = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    arr.add(i * N + j);
                }
                if (map[i][j] == 0) {
                    zero++;
                }

            }
        }

        recur(0, 0);
        if (mn == (int) 1e9) {
            mn = -1;
        }

        System.out.println(mn);
    }

    static void recur(int cur, int st) {
        if (cur == M) {
            mn = Math.min(mn, bfs());
            return;
        }
        for (int i = st; i < arr.size(); i++) {
            tmp[cur] = arr.get(i);
            recur(cur + 1, i + 1);
        }
    }

    static int bfs() {

        Queue<int[]> q = new ArrayDeque<>();

        boolean ch[][] = new boolean[52][52];
        int cnt = zero;

        if (cnt == 0) {
            return 0;
        }
        
        for (int i = 0; i < M; i++) {
            int r = tmp[i] / N;
            int c = tmp[i] % N;
            q.add(new int[]{r, c});
            ch[r][c] = true;
        }

        int t = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            t++;
            while (sz-- > 0) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                        continue;
                    }
                    if (map[nx][ny] == 1) {
                        continue;
                    }
                    if (ch[nx][ny]) {
                        continue;
                    }
                    if (map[nx][ny] == 0) {
                        cnt--;
                    }
                    if (cnt == 0) {
                        return t;
                    }

                    q.add(new int[]{nx, ny});
                    ch[nx][ny] = true;
                }
            }
        }

        return (int) 1e9;
    }
}