import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static boolean ch[] = new boolean[2020];
    ;
    static ArrayList<Integer> arr[] = new ArrayList[2002];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[A].add(B);
            arr[B].add(A);
        }

        for (int i = 0; i < N; i++) {
            dfs(i, 0);
        }
        System.out.println(0);
    }

    static void dfs(int cur, int depth) {
        if(depth >= 5){
            System.out.println(1);
            System.exit(0);
        }
        for (int nxt : arr[cur]) {
            if (ch[cur]) {
                return;
            }
            ch[cur] = true;
            dfs(nxt, depth + 1);
            ch[cur] = false;
        }
    }

}