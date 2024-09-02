import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int par[] = new int[10];
    static boolean ch[][] = new boolean[102][102];
    static int board[][] = new int[102][102];

    static int N, M;
    static int dx[] = new int[]{1, 0, -1, 0};
    static int dy[] = new int[]{0, -1, 0, 1};
    static int id = 1;

    static ArrayList<Integer[]> vec = new ArrayList<>();

    public static void main(String[] args) throws Exception {
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // flood fill로 표시
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0 || ch[i][j]) {
                    continue;
                }
                dfs(i, j);
                id++;
            }
        }

        init();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (board[x][y] == 0) {
                    continue;
                }
                int cur = board[x][y];
                for (int dir = 0; dir < 2; dir++) {
                    int nx = x;
                    int ny = y;

                    int d = 0;
                    while (true) {
                        nx += dx[dir];
                        ny += dy[dir];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                            break;
                        }

                        int nxt = board[nx][ny];
                        if (nxt == cur) {
                            break;
                        }
                        if (nxt > 0) {
                            if (d >= 2) {
                                vec.add(new Integer[]{d, cur, nxt});
                            }
                            break;
                        }

                        d++;
                    }
                }
            }
        }
//        print();
        int ret = 0;
        Collections.sort(vec, (o1, o2) -> o1[0] - o2[0]);
        for (Integer[] v : vec) {
//            System.out.println(Arrays.toString(v));
            if (find(v[1]) != find(v[2])) {
                ret += v[0];
                union(v[1], v[2]);
            }
        }
        int p = find(1);
        for (int i = 2; i < id; i++) {
            if (p != find(i)) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(ret);
    }

    static void dfs(int x, int y) {
        if (ch[x][y]) {
            return;
        }
        ch[x][y] = true;
        board[x][y] = id;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            if (board[nx][ny] == 0) {
                continue;
            }
            dfs(nx, ny);

        }
    }

    static void init() {
        for (int i = 0; i < 10; i++) {
            par[i] = i;
        }
    }

    static int find(int a) {
        if (a == par[a]) {
            return a;
        }
        return par[a] = find(par[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }
        par[b] = a;
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}