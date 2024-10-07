import java.io.*;
import java.util.*;

public class Main {

    static int t[] = new int[22];
    static int p[] = new int[22];

    static int dp[] = new int[22];

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i]는 i번째 상담을 선택했을 때 얻는 최대 수익
        for (int i = 1; i <= N; i++) {
            if (t[i] + i <= N + 1) {
                dp[i] = p[i]; // 자신선택
            }
            for (int j = 1; j < i; j++) {
                // 끝나는날 <= 자신의 시작일 && 자신의 끝나는 날 <= N + 1
                if (j + t[j] <= i && i + t[i] <= N + 1) {
                    dp[i] = Math.max(dp[j] + p[i], dp[i]);
                }
            }
        }
        int mx = 0;

        for (int i = 1; i <= N; i++) {
//            System.out.print(dp[i] + " ");
            mx = Math.max(dp[i], mx);
        }

        System.out.println(mx);
    }


}