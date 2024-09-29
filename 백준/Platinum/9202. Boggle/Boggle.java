import java.io.*;
import java.util.*;

public class Main {

    static int ROOT = 1;
    static int idx = 2;
    static final int MX = 300000 * 8 + 20;

    static boolean chk[] = new boolean[MX];
    static String ret = "";
    static int cnt = 0;
    static int score = 0;
    static int nxt[][] = new int[MX][26];
    static char board[][] = new char[4][4];
    static int dx[] = new int[]{0, 1, 0, -1, 1, 1, -1, -1};
    static int dy[] = new int[]{1, 0, -1, 0, -1, 1, -1, 1};
    static int scores[] = new int[]{0, 0, 0, 1, 1, 2, 3, 5, 11};
    static Set<String> setch = new HashSet<>();

    static void insert(String str) {
        int cur = ROOT;
        for (char c : str.toCharArray()) {
            if (nxt[cur][c2i(c)] == 0) {
                nxt[cur][c2i(c)] = idx++;
            }
            cur = nxt[cur][c2i(c)];
        }
        chk[cur] = true;
    }

    static int c2i(char c) {
        return c - 'A';
    }

    static class Node {
        int x, y, id;
        String str;
        boolean[][] visited;

        public Node(int x, int y, int id, String str, boolean[][] visited) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.str = str;
            this.visited = visited;
        }
    }

    static void bfs(Node st) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(st);

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.str.length() > 8) continue; // 최대 길이 제한

            if (chk[cur.id] && !setch.contains(cur.str)) {
                score += scores[cur.str.length()];
                cnt++;
                if (ret.length() < cur.str.length() || (ret.length() == cur.str.length() && ret.compareTo(cur.str) > 0)) {
                    ret = cur.str;
                }
                setch.add(cur.str);
            }

            for (int i = 0; i < 8; i++) {
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
                if (cur.visited[nx][ny]) continue;
                char nxc = board[nx][ny];
                if (nxt[cur.id][c2i(nxc)] == 0) continue;

                boolean[][] newVisited = new boolean[4][4];
                for (int r = 0; r < 4; r++) {
                    System.arraycopy(cur.visited[r], 0, newVisited[r], 0, 4);
                }
                newVisited[nx][ny] = true;

                q.add(new Node(nx, ny, nxt[cur.id][c2i(nxc)], cur.str + nxc, newVisited));
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            insert(br.readLine());
        }
        br.readLine();
        int B = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int b = 0; b < B; b++) {
            for (int r = 0; r < 4; r++) {
                String str = br.readLine();
                for (int c = 0; c < 4; c++) {
                    board[r][c] = str.charAt(c);
                }
            }
            ret = "";
            cnt = 0;
            score = 0;
            setch.clear();

            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (nxt[ROOT][c2i(board[r][c])] != 0) {
                        boolean[][] visited = new boolean[4][4];
                        visited[r][c] = true;
                        bfs(new Node(r, c, nxt[ROOT][c2i(board[r][c])], "" + board[r][c], visited));
                    }
                }
            }

            sb.append(score).append(" ").append(ret).append(" ").append(cnt).append("\n");

            if (b < B - 1) {
                br.readLine(); // 보드 사이의 빈 줄 처리
            }
        }
        System.out.print(sb);
    }
}