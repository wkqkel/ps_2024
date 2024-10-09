import java.io.*;
import java.util.*;

public class Main {

    static int arr[] = new int[22];
    static int N, S;
    static int ret = 0;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        S = sc.nextInt();
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        recur(0, 0);

        if (S == 0) {
            ret--;
        }
        System.out.println(ret);
    }

    static void recur(int cur, int sum) {
        if (cur == N) {
            if (sum == S) {
                ret++;
            }
            return;
        }
        recur(cur + 1, sum + arr[cur]);
        recur(cur + 1, sum);
    }
}