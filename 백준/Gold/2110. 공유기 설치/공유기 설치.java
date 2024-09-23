import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, C;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int s = 0;
        int e = arr[N - 1];

        int ret = -1;

        while (s <= e) {
            int mid = (s + e) / 2;
            int prv = 0;
            int cnt = 1;
            for (int i = 1; i < N; i++) {
                if (arr[i] - arr[prv] >= mid) {
                    prv = i;
                    cnt++;
                }
            }
            if (cnt < C) {
                e = mid - 1;
            } else {
                ret = Math.max(ret, mid);
                s = mid + 1;
            }
        }

        System.out.println(ret);
    }
}