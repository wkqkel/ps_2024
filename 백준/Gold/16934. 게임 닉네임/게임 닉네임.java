import java.io.*;
import java.util.*;

public class Main {

    static int ROOT = 1;
    static int idx = 2;
    static final int MX = 100000 * 26 + 20;

    static int chk[] = new int[MX];

    static int nxt[][] = new int[MX][26];
    static String starName[] = new String[MX];

    static void insert(String str, int wordIdx) {
        int cur = ROOT;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (nxt[cur][c2i(c)] == 0) {
                if (starName[wordIdx] == null) {
                    starName[wordIdx] = str.substring(0, i + 1);
                }
                nxt[cur][c2i(c)] = idx++;
            }
            cur = nxt[cur][c2i(c)];
        }

        chk[cur] += 1;
        if (starName[wordIdx] == null) {
            if (chk[cur] >= 2) {
                str += String.valueOf(chk[cur]);
            }
            starName[wordIdx] = str;
        }
    }

    static int c2i(char c) {
        return c - 'a';
    }


    public static void main(String[] args) throws IOException {
      
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            insert(br.readLine(), i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(starName[i]).append("\n");
        }
        System.out.println(sb);
    }


}