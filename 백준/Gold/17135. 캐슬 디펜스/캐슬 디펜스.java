import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.util.Comparator;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    static final int ARCHER_NUM = 3;
    static int N, M, D;
    static int[][] map1 = new int[22][22];
    static int[][] map2 = new int[22][22];
    static int[] archer = new int[ARCHER_NUM];
    static PriorityQueue<Enemy> pq = new PriorityQueue<>(new PqComparator());
    static Set<Enemy> set = new TreeSet<>(new SetComparator());
    static int enemyCnt = 0;
    static int mx = 0;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/_0828/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map1[i][j] = Integer.parseInt(st.nextToken());
                if (map1[i][j] == 1) {
                    enemyCnt++;
                }
            }
        }

        recur(0, 0);

        System.out.println(mx);
    }

    static void recur(int cur, int st) {
        if (cur == ARCHER_NUM) {
            solve(enemyCnt);
            return;
        }
        for (int i = st; i < M; i++) {
            archer[cur] = i;
            recur(cur + 1, i + 1);
        }
    }

    static void solve(int enemyCnt) {
        int remove = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map2[i][j] = map1[i][j];
            }
        }

        while (enemyCnt > 0) {
            set.clear();
            // 궁수 공격
            for (int i = 0; i < ARCHER_NUM; i++) {
                pq.clear();

                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        if (map2[r][c] != 1) {
                            continue;
                        }
                        int d = getDist(N, archer[i], r, c);

                        if (d > D) {
                            continue;
                        }

                        pq.add(new Enemy(d, r, c));
                    }
                }

                if (!pq.isEmpty()) {
                    set.add(pq.peek());
                }
            }
            
            // 적 제거
            for (Enemy e : set) {
                map2[e.x][e.y] = 0;
                remove++;
                enemyCnt--;
            }

            // 적 아래로 이동
            for (int r = N - 1; r >= 0; r--) {
                for (int c = M - 1; c >= 0; c--) {
                    if (map2[r][c] != 1) {
                        continue;
                    }
                    map2[r][c] = 0;
                    if (r + 1 != N) {
                        map2[r + 1][c] = 1;
                    } else {
                        enemyCnt--;
                    }
                }
            }
        }

        mx = Math.max(mx, remove);
    }

    static int getDist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    static class Enemy {
        int d, x, y;

        Enemy(int d, int x, int y) {
            this.d = d;
            this.x = x;
            this.y = y;
        }
    }

    static class SetComparator implements Comparator<Enemy> {

        @Override
        public int compare(Enemy o1, Enemy o2) {
            if (o1.x == o2.x && o1.y == o2.y) {
                return 0;
            }
            return 1;
        }
    }

    static class PqComparator implements Comparator<Enemy> {

        @Override
        public int compare(Enemy o1, Enemy o2) {
            if (o1.d == o2.d) {
                return o1.y - o2.y;
            }
            return o1.d - o2.d;
        }
    }
}