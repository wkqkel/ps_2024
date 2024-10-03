import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int v, t;

        Node(int v, int t) {
            this.v = v;
            this.t = t;
        }
    }

    static int N, M;
    static ArrayList<Node> vec[] = new ArrayList[502];
    static int tt[][] = new int[502][502];

    static void getDist(int st) {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.t - o2.t;
        });
        int time[] = new int[N + 5];
        boolean ch[] = new boolean[N + 5];

        for (int j = 0; j < N + 5; j++) {
            time[j] = (int) 1e9;
        }

        time[st] = 0;
        pq.add(new Node(st, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (ch[cur.v]) {
                continue;
            }
            ch[cur.v] = true;

            for (Node nxt : vec[cur.v]) {
                int nd = cur.t + nxt.t;

                if (ch[nxt.v]) {
                    continue;
                }

                if (nd <= time[nxt.v]) {
                    time[nxt.v] = nd;
                    pq.add(new Node(nxt.v, nd));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            tt[st][i] = time[i];
        }

    }

    public static void main(String[] args) throws Exception {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            vec[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {

                int t = Integer.parseInt(st.nextToken());

                if (i == j) {
                    continue;
                }
                vec[i].add(new Node(j, t));
            }
        }

        // 시간구하기
        for (int i = 0; i < N; i++) {
            getDist(i);
        }

        StringBuilder sb = new StringBuilder();
        // 쿼리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());
            if (tt[A][B] <= C) {
                sb.append("Enjoy other party\n");
            } else {
                sb.append("Stay here\n");
            }
        }

        System.out.println(sb);
    }

}