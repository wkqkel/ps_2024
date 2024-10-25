import java.io.*;
import java.util.*;

public class Main {
	
   static class Edge {
       int to, weight;
       Edge(int to, int weight) {
           this.to = to;
           this.weight = weight;
       }
   }
   
   static int N, M, A, B;
   static int INF = Integer.MAX_VALUE;
   static int[] dist;
   static List<Edge>[] graph, reverseGraph;
   static boolean[][] no;
   
   public static void getDist(boolean isSecond) {
       Arrays.fill(dist, INF);
       dist[A] = 0;
       
       PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
       pq.offer(new Edge(A, 0));
       
       while (!pq.isEmpty()) {
           Edge cur = pq.poll();
           if (dist[cur.to] < cur.weight) continue;
           
           for (Edge next : graph[cur.to]) {
               if (isSecond && no[cur.to][next.to]) continue;
               
               int newDist = dist[cur.to] + next.weight;
               if (newDist < 0) continue;
               
               if (newDist < dist[next.to]) {
                   dist[next.to] = newDist;
                   pq.offer(new Edge(next.to, newDist));
               }
           }
       }
       
       if (!isSecond && dist[B] != INF) {
           markShortestPaths();
       }
   }
   
   private static void markShortestPaths() {
       Queue<Integer> q = new LinkedList<>();
       q.offer(B);
       
       while (!q.isEmpty()) {
           int cur = q.poll();
           if (cur == A) continue;
           
           for (Edge prev : reverseGraph[cur]) {
               if (dist[cur] == dist[prev.to] + prev.weight) {
                   if (!no[prev.to][cur]) {
                       no[prev.to][cur] = true;
                       q.offer(prev.to);
                   }
               }
           }
       }
   }
   
   public static void main(String[] args) throws Exception {
	   // System.setIn(new FileInputStream("src/input.txt"));
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();
       
       while (true) {
           StringTokenizer st = new StringTokenizer(br.readLine());
           N = Integer.parseInt(st.nextToken());
           M = Integer.parseInt(st.nextToken());
           
           if (N == 0 && M == 0) break;
           
           st = new StringTokenizer(br.readLine());
           A = Integer.parseInt(st.nextToken());
           B = Integer.parseInt(st.nextToken());
           
           // 초기화
           dist = new int[N];
           no = new boolean[N][N];
           graph = new ArrayList[N];
           reverseGraph = new ArrayList[N];
           for (int i = 0; i < N; i++) {
               graph[i] = new ArrayList<>();
               reverseGraph[i] = new ArrayList<>();
           }
           
           // 간선 입력
           for (int i = 0; i < M; i++) {
               st = new StringTokenizer(br.readLine());
               int u = Integer.parseInt(st.nextToken());
               int v = Integer.parseInt(st.nextToken());
               int p = Integer.parseInt(st.nextToken());
               graph[u].add(new Edge(v, p));
               reverseGraph[v].add(new Edge(u, p));
           }
           
           getDist(false);  // 최단 경로 찾기
           getDist(true);   // 두 번째로 짧은 경로 찾기
           
           sb.append(dist[B] == INF ? -1 : dist[B]).append("\n");
       }
       System.out.print(sb);
       br.close();
   }
}