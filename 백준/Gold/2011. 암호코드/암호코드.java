import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String str = sc.next();
//        String str = "25114";
        int N = str.length();
        int mod = 1000000;

        // dp[i][j]는 i까지 봤을때, j를 끝으로 하는 경우의 수
        int dp[][] = new int[5002][32];

        dp[1][(int) (str.charAt(0) - '0')] = 1;
        for (int i = 2; i <= N; i++) {
            int curI = (int) (str.charAt(i - 1) - '0');

            // 1. 현재꺼를 새로 붙이는 경우
            for (int j = 1; j <= 26; j++) {
                dp[i][curI] += dp[i - 1][j] % mod;
            }

            // 2. 이전에 1, 2이면 현재꺼와 이어붙일 수 있음.
            for (int j = 1; j <= 2; j++) {
                if ((j == 1) || (curI <= 6)) {
                    int last = Integer.valueOf(j + "" + curI);
                    dp[i][last] += dp[i - 1][j] % mod;
                }
            }
//            System.out.println(Arrays.toString(dp[i]));
        }
        int ret = 0;
        for (int i = 1; i <= 26; i++) {
            ret += dp[N][i] % mod;
        }
        System.out.println(ret % mod);
    }
}