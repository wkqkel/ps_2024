import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Block {

    public int v;
    public boolean isMerged;

    public Block(int v) {
        this.v = v;
        this.isMerged = false;
    }
}

public class Solution {

    static int n;

    static Block[][] board = new Block[24][24];
    static Map<String, Integer> DIRS_MAP = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        DIRS_MAP.put("right", 0);
        DIRS_MAP.put("down", 1);
        DIRS_MAP.put("left", 2);
        DIRS_MAP.put("up", 3);

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            String cmd = st.nextToken();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    board[i][j] = new Block(v);
                }
            }

            for (int i = 0; i < DIRS_MAP.get(cmd); i++) {
                rotate();
            }

            for (int i = 0; i < n; i++) {
                move(board[i]);
            }
            for (int i = 0; i < 4 - DIRS_MAP.get(cmd); i++) {
                rotate();
            }

            sb.append("#").append(t).append(" ").append("\n").append(printMap());
        }
        System.out.println(sb);
    }

    private static void move(Block[] arr) {
        for (int i = n - 2; i >= 0; i--) {
            for (int cur = i; cur < n - 1; cur++) {
                int nxt = cur + 1;
                if (arr[nxt].v == 0) {
                    Block tmp = arr[cur];
                    arr[cur] = arr[nxt];
                    arr[nxt] = tmp;
                } else if (arr[cur].v == arr[nxt].v && !arr[cur].isMerged && !arr[nxt].isMerged) {
                    Block tmp = arr[cur];
                    tmp.v *= 2;
                    tmp.isMerged = true;
                    arr[cur] = new Block(0);
                    arr[nxt] = tmp;
                } else {
                    break;
                }
            }
        }


    }

    static void rotate() {
        Block tmp[][] = new Block[24][24];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] = board[j][n - i - 1];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = tmp[i][j];
            }
        }
    }

    static String printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j].v).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}