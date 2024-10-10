import java.io.*;
import java.util.*;



public class Main {


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // dp[i] 는 자연수 i의 제곱수들의 합 표현시 항의 최소개수
        // 틀린접근: x는 i와 가장가까운 제곱수 일 때 dp[i] = 1 + dp[i -x]
        // 가장가까운제곱수는 i를 sqrt 한뒤 반올림한뒤 제곱
        // 반례 41=5*5+4*4 인데 최댓값시 41=6*6+2*2+1*1 됨
        // 보다 적은 제곱수에 대해 모두 봐줘야함.

        int dp[] = new int[100020];

        for (int i = 0; i < 100020; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        System.out.println(dp[N]);
    }


}