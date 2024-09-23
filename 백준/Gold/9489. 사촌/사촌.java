import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static HashMap<Integer, Integer> to = new HashMap<>();
    static int[] par = new int[1020];
    static int[] arr = new int[1020];
    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            if (N == 0 && K == 0) {
                break;
            }

            arr[0] = -1;
            par[0] = -1;
            int idx = -1;
            int t = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                if (arr[i - 1] + 1 != arr[i]) {
                    idx++;
                }
                if (arr[i] == K) {
                    t = i;
                }
                par[i] = idx;
            }

            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (par[i] != par[t] && par[par[i]] == par[par[t]]) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}