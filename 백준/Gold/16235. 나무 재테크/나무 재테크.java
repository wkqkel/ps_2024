import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int A[][] = new int[12][12];
    static ArrayList<Tree> trees = new ArrayList<>();
    static int map1[][] = new int[12][12]; // 양분
    static ArrayList<Tree> map2[][] = new ArrayList[12][12];
    static ArrayList<Tree> dead = new ArrayList<>();
    static int dr[] = new int[]{1, 0, -1, 0, 1, 1, -1, -1};
    static int dc[] = new int[]{0, 1, 0, -1, 1, -1, 1, -1};

    static void solution() {
        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(trees.size());
    }

    static void spring() {
        ArrayList<Tree> ntrees = new ArrayList<>();
        ArrayList<Tree> nmap2[][] = new ArrayList[12][12];
        ArrayList<Tree> ndead = new ArrayList<>();

        Collections.sort(trees, (o1, o2) -> o1.age - o2.age);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nmap2[i][j] = new ArrayList<>();
            }
        }

        for (Tree t : trees) {
            if (map1[t.r][t.c] < t.age) {
                ndead.add(t);
                continue;
            }
            map1[t.r][t.c] -= t.age;
            t.age++;

            ntrees.add(t);
            nmap2[t.r][t.c].add(t);
        }

        dead = ndead;
        map2 = nmap2;
        trees = ntrees;
    }

    static void summer() {
        for (Tree d : dead) {
            map1[d.r][d.c] += d.age / 2;
        }
    }

    static void fall() {
        ArrayList<Tree> ntrees = new ArrayList<>();
        for (Tree t : trees) {
            ntrees.add(t);
            if (t.age % 5 != 0) {
                continue;
            }

            for (int dir = 0; dir < 8; dir++) {
                int nr = t.r + dr[dir];
                int nc = t.c + dc[dir];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }
                Tree nt = new Tree(nr, nc, 1);
                ntrees.add(nt);
                map2[nr][nc].add(nt);
            }
        }
        trees = ntrees;
    }

    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map1[i][j] += A[i][j];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map2[i][j] = new ArrayList<>();
                map1[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            r--;
            c--;
            Tree tree = new Tree(r, c, age);
            trees.add(tree);
            map2[r][c].add(tree);
        }

        solution();
    }

    static class Tree {

        int r, c, age;

        Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }
    }
}