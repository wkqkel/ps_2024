import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int ROOT = 1;
    static int MX = 10000 * 10 + 5;
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
    // 길이순 정렬 -> 같을 땐, 숫자 낮은게 앞에 오게

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
        return c - '0';
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {

            for (int i = 0; i < MX; i++) {
                chk[i] = false;
                for (int j = 0; j < 26; j++) {
                    nxt[i][j] = -1;
                }
            }
            idx = 2;

            int N = Integer.parseInt(br.readLine());
            String arr[] = new String[N];
            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine();
            }

            Arrays.sort(arr, (o1, o2) -> {
                if (o1.length() == o2.length()) {
                    return o2.compareTo(o1);
                }

                return o2.length() - o1.length();
            });

            String ret = "YES";
            for (int i = 0; i < N; i++) {

                if (find(arr[i])) {
                    ret = "NO";
                }
                insert(arr[i]);
            }

            sb.append(ret).append("\n");
        }

        System.out.println(sb);
    }
}