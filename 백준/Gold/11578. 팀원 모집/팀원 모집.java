import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.*;

/**
 * 한명당 최대 10개 풀수있음 모든 문제를 풀수있는 최정예 팀
 * <p>
 * 팀원을 넣고, 안넣고. 2^10 모든 문제를 풀수있는지 10 * 10
 */
public class Main {

    static int N, M;
    static int ret = Integer.MAX_VALUE;
    static boolean ch[] = new boolean[14];
    static List<Integer> ps[] = new List[14]; // i번 학생이 풀수있는 문제들

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 14; i++) {
            ps[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());

            for (int j = 0; j < len; j++) {
                int v = Integer.parseInt(st.nextToken());
                ps[i].add(v);
            }
        }

        recur(0);
        if (ret == Integer.MAX_VALUE) {
            ret = -1;
        }
        System.out.println(ret);
    }

    public static void recur(int cur) {
        if (cur == M) {
            boolean[] sol = new boolean[N + 1];
            int cnt = 0;
            for (int i = 0; i < M; i++) {
                if (!ch[i]) {
                    continue;
                }
                for (int p : ps[i]) {
                    sol[p] = true;
                }
                cnt++;
            }
            boolean isAll = true;
            for (int i = 1; i <= N; i++) {
                if (!sol[i]) {
                    isAll = false;
                    break;
                }
            }
            if (isAll) {
                ret = Math.min(ret, cnt);
            }
            return;
        }
        ch[cur] = true;
        recur(cur + 1);
        ch[cur] = false;
        recur(cur + 1);
    }
}