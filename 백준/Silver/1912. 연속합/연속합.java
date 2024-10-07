import java.io.*;
import java.util.*;

public class Main {

    static int arr[] = new int[100002];
    static int dp[] = new int[100002]; // 최대합 1e5 * 1e3 => int가능

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
//
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // dp[i]는 i를 마지막으로 선택했을 때, i까지 최대 연속수열의 합
        for (int i = 1; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
        }

        int mx = Integer.MIN_VALUE; // 음수도 올수있으므로 가장 작은음수로 초기화
        for (int i = 1; i <= N; i++) {
            mx = Math.max(mx, dp[i]);
        }

        System.out.println(mx);
    }


}