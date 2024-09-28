import java.io.*;
import java.util.*;

public class Main {

    static int ROOT = 1;
    static int idx = 2;
    static final int MX = 1000 * 15 + 20;
    static Map<String, Integer> nxt[] = new TreeMap[MX];
    //    static boolean chk[] = new boolean[MX];
    static int dep[] = new int[MX];
    static String name[] = new String[MX];
    static StringBuilder sb = new StringBuilder();

    static void insert(String[] strs) {
        int cur = ROOT;
        dep[cur] = -1;
        for (String str : strs) {
            if (nxt[cur] == null) {
                nxt[cur] = new TreeMap<>();
            }
            if (nxt[cur].get(str) == null) {
                nxt[cur].put(str, idx++);
            }
            int nv = nxt[cur].get(str);
            name[nv] = str;
            dep[nv] = dep[cur] + 1;
            cur = nxt[cur].get(str);
        }
    }

    static void dfs(int cur) {
        if (cur != ROOT) {
            sb.append("--".repeat(dep[cur]))
                    .append(name[cur].substring(0,
                            name[cur].length() - String.valueOf(dep[cur]).length()))
                    .append("\n");
        }

        if (nxt[cur] != null) {
            for (Map.Entry<String, Integer> e : nxt[cur].entrySet()) {
                dfs(e.getValue());
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            String[] strs = new String[K];
            for (int j = 0; j < K; j++) {
                strs[j] = st.nextToken() + String.valueOf(j);
            }
            insert(strs);
        }

        dfs(ROOT);
        System.out.println(sb);
    }


}