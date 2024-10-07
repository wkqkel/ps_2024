import java.io.*;
import java.util.*;

public class Main {

    static int arr[] = new int[1002];
    static int dp[] = new int[1002];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // dp[i]는 i가 선택된 증가하는 부분수열의 합
        for (int i = 0; i < N; i++) {
            dp[i] = arr[i]; // 자신보다 작은게 없다면 최소 자신
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[j] + arr[i], dp[i]);
                }
            }
        }

        int mx = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            mx = Math.max(mx, dp[i]);
        }

        System.out.println(mx);
    }


}