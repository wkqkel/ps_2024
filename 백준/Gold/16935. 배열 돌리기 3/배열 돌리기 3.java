import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {


    static int n, m, r;
    static int map[][] = new int[302][302];
    static int tmp[][] = new int[302][302];
    static Map<Integer, int[]> area = new HashMap<>();

    static void cal1() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[i][j] = map[n - i - 1][j];
            }
        }

        copy();
    }

    static void cal2() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[i][j] = map[i][m - j - 1];
            }
        }

        copy();
    }

    static void cal3() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[j][n - i - 1] = map[i][j];
            }
        }

        int t = n;
        n = m;
        m = t;
        setArea();
        copy();
    }

    static void cal4() {
        cal3();
        cal3();
        cal3();
    }

    static void cal5() {
        // 1->2 , 2->3, 3->4, 4->1
        move(area.get(1), area.get(2));
        move(area.get(2), area.get(3));
        move(area.get(3), area.get(4));
        move(area.get(4), area.get(1));
        copy();
    }

    static void cal6() {
        // 1->4, 4->3,3->2,2->1
        move(area.get(1), area.get(4));
        move(area.get(4), area.get(3));
        move(area.get(3), area.get(2));
        move(area.get(2), area.get(1));
        copy();
    }

    static void move(int[] from, int[] to) {
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                tmp[to[0] + i][to[1] + j] = map[from[0] + i][from[1] + j];
            }
        }
    }

    static void copy() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void setArea() {
        // 1번그룹 0~hn, 0~hm 앞자리 0 0
        // 2번그룹 0~hn, hm~m 앞자리 0 hm
        // 3번그룹 hn~n, hm~m 앞자리 hn hm
        // 4번그룹 hn~n, 0~hm 앞자리 hn 0
        int hn = n / 2;
        int hm = m / 2;
        area.put(1, new int[]{0, 0});
        area.put(2, new int[]{0, hm});
        area.put(3, new int[]{hn, hm});
        area.put(4, new int[]{hn, 0});
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        setArea();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) {
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                cal1();
            }
            else if (cmd == 2) {
                cal2();
            }
            else if (cmd == 3) {
                cal3();
            }
            else if (cmd == 4) {
                cal4();
            }
            else if (cmd == 5) {
                cal5();
            }
            else if (cmd == 6) {
                cal6();
            }
        }
        print();
    }
}