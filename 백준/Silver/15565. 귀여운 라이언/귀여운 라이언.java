import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.*;

/**
 * 라이언은 1, 어피치는 0 누적합이 K 이상인 최소집합크기 1 1 1 0 2 0 3 0 0 4 arr[1] = idx;
 */
public class Main {

    static final int MX = 1000000;

    static final int[] psum = new int[MX + 2];
    static int[] idx = new int[MX + 2];

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int mx = 0;
        int mn = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int v = Integer.parseInt(st.nextToken());

            if (v == 2) {
                continue;
            }

            psum[i] = ++mx;
            idx[mx] = i;
//
            if (mx - K + 1 > 0 && idx[mx - K + 1] != 0) {
                mn = Math.min(mn, i - idx[mx - K + 1] + 1);
            }
        }

        if (mn == Integer.MAX_VALUE) {
            mn = -1;
        }

        System.out.println(mn);
    }
}