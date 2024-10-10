import java.io.*;
import java.util.*;

public class Main {

    static Map<Integer, Long> f = new HashMap<>();

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int mod = 1000000000;
        f.put(0, 0l);
        f.put(1, 1l);
        for (int i = -1; i >= -1000000; i--) {
            f.put(i, (f.get(i + 2) - f.get(i + 1)) % mod);
        }
        for (int i = 2; i <= 1000000; i++) {
            f.put(i, (f.get(i - 1) + f.get(i - 2)) % mod);
        }
        long ret = f.get(N);
        System.out.println(ret == 0 ? 0 : ret > 0 ? 1 : -1);
        System.out.println(Math.abs(ret) % mod);
    }

}