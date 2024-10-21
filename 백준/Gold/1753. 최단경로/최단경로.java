import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX_VERTEX = 20000;

    static int V, E, K;
    static int dist[] = new int[MAX_VERTEX + 1];
    static List<int[]> edges[] = new List[MAX_VERTEX + 1];

    static void getDist(int st) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        boolean ch[] = new boolean[MAX_VERTEX + 1];
        for (int i = 1; i <= V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        pq.add(new int[]{0, st});
        dist[st] = 0;

        while (!pq.isEmpty()) {
            int cur[] = pq.poll();

            if (ch[cur[1]]) {
                continue;
            }
            ch[cur[1]] = true;
            for (int[] nxt : edges[cur[1]]) {
                int nd = nxt[0] + cur[0];

                if (nd <= dist[nxt[1]]) {
                    dist[nxt[1]] = nd;
                    pq.add(new int[]{nd, nxt[1]});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        for (int i = 1; i <= V; i++) {
            edges[i] = new LinkedList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[u].add(new int[]{w, v});
        }

        getDist(K);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(dist[i]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}