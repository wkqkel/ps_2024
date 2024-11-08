import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K; // 맵크기,상어갯수,유통기한
    static Smell s_map[][] = new Smell[22][22];
    static int map[][] = new int[22][22];
    static Shark[] shark_pool = new Shark[420];

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 맵 입력 받기 및 상어 생성
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 0) {
                    int id = map[i][j];
                    shark_pool[id] = new Shark(id, i, j);
                }
            }
        }

        // 초기 냄새 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            shark_pool[i].dir = Integer.parseInt(st.nextToken());
        }

        // 우선순위 입력받기
        for (int i = 1; i <= M; i++) {

            Shark shark = shark_pool[i];
            for(int dir = 1; dir <= 4; dir++) {
                int[] dirs = new int[4];
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    dirs[j] = Integer.parseInt(st.nextToken());
                }
                shark.dirs[dir] = dirs;
            }
        }

        smell();
        // 턴 진행하기
        int t;

        for (t = 0; t <= 1000; t++) {
//            System.out.println("----------" +  t);
            // 냄새 카운트 --
//            printMap();


            if (count() == 1)
                break;

            // 상어 이동
            move();
            // 냄새 뿌림
            minusSmell();
            smell();

        }
        if(t == 1001) t = -1;
        System.out.println(t);
    }


    static void move() {
        int[][] nmap = new int[22][22];

        for (int i = 1; i <= M; i++) {
            Shark shark = shark_pool[i];
            if (shark.isDead)
                continue;
            boolean moved = false;

            // 먼저 냄새 없는 곳 이동
            for (int ndir : shark.dirs[shark.dir]) {
//                System.out.println(shark + " " + Arrays.toString(shark.dirs[shark.dir]));
                int nx = shark.x + Shark.dx[ndir];
                int ny = shark.y + Shark.dy[ndir];
                // 맵 벗어나면
                if (nx <= 0 || nx > N || ny <= 0 || ny > N)
                    continue;
                // 냄새가 존재하지않으면
                if (s_map[nx][ny] != null)
                    continue;
                // 이동하려는데 이미 누가 있으면, 죽음

                if (nmap[nx][ny] != 0 && nmap[nx][ny] < shark.id) {
//					System.out.println(nmap[nx][ny]);
//					System.out.println("죽음1" + shark.id);
                    shark.isDead = true;
                    moved= true;
                    break;
                }
                nmap[nx][ny] = shark.id;
                shark.x = nx;
                shark.y = ny;
                shark.dir = ndir;
                moved = true;
                break;
            }

            // 이동했으면 다음상어
            if (moved)
                continue;

            // 내 냄새 인 곳 이동
            for (int ndir : shark.dirs[shark.dir]) {
                int nx = shark.x + Shark.dx[ndir];
                int ny = shark.y + Shark.dy[ndir];
                // 맵 벗어나면
                if (nx <= 0 || nx > N || ny <= 0 || ny > N)
                    continue;
                // 내 냄새가 아니면
                if (s_map[nx][ny] == null || s_map[nx][ny].i != shark.id)
                    continue;
                // 이동하려는데 이미 누가 있으면, 죽음
                if (nmap[nx][ny] != 0 && nmap[nx][ny] < shark.id) {
                    System.out.println("죽음2" + shark.id);
                    shark.isDead = true;

                    break;
                }
                nmap[nx][ny] = shark.id;
                shark.x = nx;
                shark.y = ny;
                shark.dir = ndir;
                break;
            }
        }

        map = nmap;
    }

    static int count() {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] != 0)
                    cnt++;
            }
        }
        return cnt;
    }

    static void smell() {
        for (int i = 1; i <= M; i++) {
            Shark shark = shark_pool[i];
            if (shark.isDead)
                continue;
            s_map[shark.x][shark.y] = new Smell(shark.id, K);
        }
    }

    static void minusSmell() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Smell cur = s_map[i][j];
                if (cur != null) {
                    cur.t--;

                    if (cur.t == 0)
                        s_map[i][j] = null;
                }

            }
        }
    }

    static void printMap() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    static void printSMap() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(s_map[i][j] + " ");
            }
            System.out.println();
        }

    }
}

class Shark {
    static int dx[] = new int[] { 0, -1, 1, 0, 0 };
    static int dy[] = new int[] { 0, 0, 0, -1, 1 };

    int id, x, y, dir;
    int dirs[][] = new int[5][5];
    boolean isDead = false;

    public Shark(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Shark [id=" + id + ", x=" + x + ", y=" + y + ", dir=" + dir +
                ", isDead=" + isDead + "]";
    }


}

class Smell {
    int i, t;

    public Smell(int i, int t) {
        this.i = i;
        this.t = t;
    }

    @Override
    public String toString() {
        return "[i=" + i + ", t=" + t + "]";
    }


}