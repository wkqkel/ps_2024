import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static Map<Integer, List<Fire>> map = new HashMap<>();
    static List<Fire> list = new ArrayList<>();
    static int[] nd = new int[]{0, 2, 4, 6, 1, 3, 5, 7};

    static class Fire {

        static int dr[] = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
        static int dc[] = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

        int r, c, m, s, d;

        Fire(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        void move() {
            for (int i = 0; i < s; i++) {
                r += dr[d];
                c += dc[d];

                if (r > N) {
                    r = 1;
                }
                if (r < 1) {
                    r = N;
                }
                if (c > N) {
                    c = 1;
                }
                if (c < 1) {
                    c = N;
                }
            }
        }


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.add(new Fire(r, c, m, s, d));
        }

        for (int k = 0; k < K; k++) {
            for (Fire f : list) {
                f.move();
                int idx = f.r * N + f.c;
                if (map.containsKey(idx)) {
                    map.get(idx).add(f);
                } else {
                    List<Fire> l = new ArrayList<>();
                    l.add(f);
                    map.put(idx, l);
                }
            }
            list.clear();

            for (List<Fire> li : map.values()) {
                if (li.size() == 1) {
                    list.add(li.get(0));
                    continue;
                }

                int sumM = 0;
                int sumS = 0;

                boolean flag = true;
                int first = li.get(0).d % 2;
                for (Fire f : li) {
                    sumM += f.m;
                    sumS += f.s;

                    if (f.d % 2 != first) {
                        flag = false;
                    }
                }

                int newM = sumM / 5;
                int newS = sumS / li.size();
                int newDir = flag ? 0 : 4;
                int r = li.get(0).r;
                int c = li.get(0).c;

                if (newM == 0) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    list.add(new Fire(r, c, newM, newS, nd[newDir++]));
                }
            }

            map.clear();
        }
        int ret = 0;

        for (Fire f : list) {
            ret += f.m;
        }

        System.out.println(ret);

    }


}