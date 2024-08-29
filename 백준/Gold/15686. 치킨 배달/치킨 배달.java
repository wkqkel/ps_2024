import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int HOUSE = 1, STORE = 2;
    static int board[][] = new int[52][52];
    static int N, M;
    static ArrayList<Integer[]> stores = new ArrayList<>();
    static ArrayList<Integer[]> houses = new ArrayList<>();
    static int selected[] = new int[22];
    static int mn = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                // 집과 치킨집 리스트에 저장
                if (board[r][c] == HOUSE) {
                    houses.add(new Integer[]{r, c});
                } else if (board[r][c] == STORE) {
                    stores.add(new Integer[]{r, c});
                }
            }
        }

        recur(0, 0);

        System.out.println(mn);
    }

    static void recur(int cur, int st) {
        if (cur == M) {
            solution();
            return;
        }

        for (int i = st; i < stores.size(); i++) {
            selected[cur] = i;
            recur(cur + 1, i + 1);
        }
    }

    static void solution() {
        int cityDist = 0;
        for (Integer[] house : houses) {
            int chickenDist = Integer.MAX_VALUE;
            for (int i = 0; i < M; i++) {
                int idx = selected[i];
                Integer[] store = stores.get(idx);
                int dist = getDist(house[0], house[1], store[0], store[1]);
                chickenDist = Math.min(dist, chickenDist);
            }
            cityDist += chickenDist;
        }
        mn = Math.min(mn, cityDist);
    }

    static int getDist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}