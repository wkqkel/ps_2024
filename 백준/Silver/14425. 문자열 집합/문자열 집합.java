import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        HashSet<String> set = new HashSet<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String q[] = new String[10002];
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            q[i] = br.readLine();

        }
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (set.contains(q[i])) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}