import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

class Pair {

    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }


}

public class Main {

    static List<Pair>[] vec = new List[10002];

    static int dists[] = new int[10002];

    public static void dfs(int cur, int par) {
        for (Pair pair : vec[cur]) {
            int child = pair.x;
            int dist = pair.y;
            if (child == par) {
                continue;
            }
            dists[child] = dist + dists[cur];
            dfs(child, cur);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i <= n; i++) {
            vec[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            vec[a].add(new Pair(b, c));
            vec[b].add(new Pair(a, c));
        }

        StringBuilder sb = new StringBuilder();
        int root = 1;
        dfs(root, 0);
        int mx = -1;
        for (int i = 1; i <= n; i++) {
            if (mx < dists[i]) {
                mx = dists[i];
                root = i;
            }
            dists[i] = 0;
        }

        dfs(root, 0);

        for (int i = 1; i <= n; i++) {
            if (mx < dists[i]) {
                mx = dists[i];
            }
        }

        System.out.println(mx);
    }
}