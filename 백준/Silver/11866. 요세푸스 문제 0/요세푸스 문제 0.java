import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            q.add(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                q.add(q.poll());
            }
            sb.append(q.poll());
            if (i != n - 1) sb.append(", ");
        }
        sb.append(">");
        System.out.println(sb);
    }
}