import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int arr[] = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int s = 0;
        int e = 0;
        int ret = 0;
        while (e < N) {
//            System.out.println(s + " " + e);
            while (e - s >= 2 && arr[s] + arr[s + 1] <= arr[e]) {
                s++;
            }
            ret = Math.max(ret, e - s + 1);
            e += 1;
        }

        System.out.println(ret);
    }


}