import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int ROOT = 1;
    static int MX = 10000 * 500 + 5;
    static int nxt[][] = new int[MX][26];
    static boolean chk[] = new boolean[MX];
    static int idx = 2;

    static void insert(String s) {
        int cur = ROOT;
        for (char c : s.toCharArray()) {
            if (nxt[cur][c2i(c)] == -1) {
                nxt[cur][c2i(c)] = idx++;
            }
            cur = nxt[cur][c2i(c)];
        }
        chk[cur] = true;
    }

    static boolean find(String s) {
        int cur = ROOT;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (nxt[cur][c2i(c)] == -1) {
                return false;
            }
            cur = nxt[cur][c2i(c)];
        }
        return true;
    }

    static int c2i(char c) {
        return c - 'a';
    }

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < MX; i++) {
            chk[i] = false;
            for (int j = 0; j < 26; j++) {
                nxt[i][j] = -1;
            }
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            insert(br.readLine());
        }
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (find(br.readLine())) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}