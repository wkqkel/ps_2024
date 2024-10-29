import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Integer>[] vec = new List[100002];
    static int parents[] = new int[100002];
    public static void dfs(int cur, int par) {
        for (int child : vec[cur]) {
            if (child == par) {
                continue;
            }
            parents[child] = cur;
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

            vec[a].add(b);
            vec[b].add(a);
        }
        StringBuilder sb = new StringBuilder();
        dfs(1, -1);
        for(int i = 2; i <= n; i++){
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb);
    }
}