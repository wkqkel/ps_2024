import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int arr[][] = new int[102][102];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int ret = 0;
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    cnt += getMin(stk);
                } else {
                    stk.add(arr[i][j]);
                }
            }

            if (!stk.empty()) {
                cnt += getMin(stk);
            }
            ret += cnt;
        }

        System.out.println(ret);
    }

    static int getMin(Stack<Integer> stk) {

        int row1 = 0;
        int cnt1 = 0;

        int row2 = 0;
        int cnt2 = 0;

        if (stk.empty()) {
            return 0;
        }

        while (!stk.empty()) {
            int v = stk.pop();
            if (v == 1) {
                if (row1 > 0) {
                    cnt1 += 1;
                }
                row1 = 0;

                row2++;
            } else {
                row1++;

                if (row2 > 0) {
                    cnt2 += 1;
                }
                row2 = 0;
            }
        }

        if (row1 != 0) {
            cnt1 += row1;
        }
        if (row2 != 0) {
            cnt2 += row2;
        }

        return Math.min(cnt1, cnt2) + 1;
    }
}