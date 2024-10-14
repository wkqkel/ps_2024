import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Horse {

        int id, x, y, dir;

        public Horse(int id, int x, int y, int dir) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Horse{" +
                    "id=" + id +
                    ", x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    '}';
        }
    }

    static int N, K;

    static int color[][] = new int[14][14];
    static Horse horses[] = new Horse[14];
    static List<Integer> mapQ[][] = new List[14][14];
    static int dx[] = new int[]{0, 0, -1, 1};
    static int dy[] = new int[]{1, -1, 0, 0};
    static int op[] = new int[]{1, 0, 3, 2};

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
                mapQ[i][j] = new LinkedList<>();
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;

            horses[i] = new Horse(i, x, y, dir);
            mapQ[x][y].add(i);
        }

        for (int t = 1; t <= 1000; t++) {
            for (int i = 1; i <= K; i++) {

                Horse h = horses[i];
                int nx = h.x + dx[h.dir];
                int ny = h.y + dy[h.dir];
                if (mapQ[h.x][h.y].size() >= 4) {
                    System.out.println(t);
                    return;
                }
                int idx = mapQ[h.x][h.y].indexOf(h.id);
                List<Integer> moves = new LinkedList<>();

                // 파란색인경우
                if (overMap(nx, ny) || color[nx][ny] == 2) {
                    h.dir = op[h.dir];
                    nx = h.x + dx[h.dir];
                    ny = h.y + dy[h.dir];

                    if (overMap(nx, ny) || color[nx][ny] == 2) {
                        continue;
                    }
                }

                for (int j = idx; j < mapQ[h.x][h.y].size(); j++) {
                    moves.add(mapQ[h.x][h.y].get(j));
                }

                for (int j = mapQ[h.x][h.y].size() - 1; j >= idx; j--) {
                    mapQ[h.x][h.y].remove(j);
                }
                
                // 빨간색인경우
                if (color[nx][ny] == 1) {
                    Collections.reverse(moves);
                }

                // 이동
                for (int id : moves) {
                    horses[id].x = nx;
                    horses[id].y = ny;
                    mapQ[nx][ny].add(id);
                }

                if (mapQ[nx][ny].size() >= 4) {
                    System.out.println(t);
                    return;
                }

            }
        }
        System.out.println(-1);
    }

    static boolean overMap(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}