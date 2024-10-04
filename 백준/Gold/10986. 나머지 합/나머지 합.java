import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] cnt = new long[M];
        long sum = 0;
        long ret = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sum += Integer.parseInt(st.nextToken());
            sum %= M;
            cnt[(int) sum]++;
        }

        for (int i = 0; i < M; i++) {
            ret += cnt[i] * (cnt[i] - 1) / 2;
        }

        ret += cnt[0];

        System.out.println(ret);
    }
}