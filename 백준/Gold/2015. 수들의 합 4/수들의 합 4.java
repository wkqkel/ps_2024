import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream(("src/_0925/input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        long[] psum = new long[N + 2];
        HashMap<Long, Long> map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        long ret = 0;
        map.put(0L, 1L);
        for (int i = 1; i <= N; i++) {
            psum[i] = psum[i - 1] + Long.parseLong(st.nextToken());
            // 현재의 누적합에서 K를 뺀 값이 몇번나왔는지
            ret += map.getOrDefault(psum[i] - K, 0L);
            map.put(psum[i], map.getOrDefault(psum[i], 0L) + 1);
        }
        System.out.println(ret);
    }
}