import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int N, M;
    static ArrayList<Integer> arr1[] = new ArrayList[502];
    static ArrayList<Integer> arr2[] = new ArrayList[502];


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            for (int i = 0; i <= N; i++) {
                arr1[i] = new ArrayList<>();
                arr2[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr1[a].add(b);
                arr2[b].add(a);
            }

            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                int child = bfs(i, arr1);
                int par = bfs(i, arr2);
                if (child + par == N - 1) {
                    cnt++;
                }
            }

            sb.append("#").append(t).append(" ").append(cnt).append("\n");

        }
        System.out.println(sb);

    }

    static int bfs(int x, ArrayList<Integer> arr[]) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] ch = new boolean[502];
        q.add(x);
        ch[x] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            cnt += sz;
            while (sz-- > 0) {
                int cur = q.poll();
                for (int nxt : arr[cur]) {
                    if (ch[nxt]) {
                        continue;
                    }
                    q.add(nxt);
                    ch[nxt] = true;
                }
            }
        }
        return cnt - 1;
    }

}