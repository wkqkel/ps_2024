import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Set<String> set = new HashSet<>();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            String v = sc.next();
            if ("ENTER".equals(v)) {
                ret += set.size();
                set.clear();
            } else {
                set.add(v);
            }
        }
        ret += set.size();
        System.out.println(ret);
    }
}