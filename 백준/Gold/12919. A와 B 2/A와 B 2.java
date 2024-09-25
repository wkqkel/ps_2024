import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static boolean flag = false;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream(("src/_0926/input.txt")));
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String t = sc.next();
//        System.out.println(t.substring(0, t.length() - 1));
        recur(s, t);
        int ret = flag ? 1 : 0;
        System.out.println(ret);
    }

    static void recur(String s, String t) {

        if (s.length() >= t.length()) {
            if (s.equals(t)) {
                flag = true;
            }
            return;
        }
        if (t.charAt(t.length() - 1) == 'A') {
            recur(s, t.substring(0, t.length() - 1));
        }

        if (t.charAt(0) == 'B') {
            recur(s, new StringBuilder(t.substring(1)).reverse().toString());
        }
    }
}