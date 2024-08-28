import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int dr[] = new int[]{-1, 1, 0, 0};
    static int dc[] = new int[]{0, 0, -1, 1};
    static int op[] = new int[]{1, 0, 3, 2};

    static final int MX = 1020;

    static int sz[] = new int[MX];
    static int dir[] = new int[MX];
    static int map[][] = new int[102][102];
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            N = n;

            // map 초기화
            for (int r = 0; r < 102; r++) {
                for (int c = 0; c < 102; c++) {
                    map[r][c] = 0;
                }
            }

            // 입력받기
            for (int id = 1; id <= k; id++) { // id 1-indexed
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map[r][c] = id;
                sz[id] = Integer.parseInt(st.nextToken());
                dir[id] = Integer.parseInt(st.nextToken()) - 1; // dir 0-indexed
            }

            while (m-- > 0) {
                // 중복방지위함
                int nmap[][] = new int[102][102];
                int nsz[] = new int[MX];

                // sz초기화
                for (int i = 1; i <= k; i++) {
                    nsz[i] = sz[i];
                }

                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {
                        if (map[r][c] == 0) {
                            continue;
                        }

                        int a = map[r][c];
                        int d = dir[a];
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        int b = nmap[nr][nc];
                        int mx = sz[a] >= sz[b] ? a : b; // sz가 큰 id
                        int mn = mx ^ a ^ b; // sz가 작은 id

                        if (nr == 0 || nr == n - 1 || nc == 0 || nc == n - 1) { // 가장자리라면
                            nsz[a] /= 2;
                            dir[a] = op[d];
                        } else if (nmap[nr][nc] > 0) { // 이미 방문했다면
                            nsz[mx] += nsz[mn];
                            nsz[mn] = 0;
                        }

                        map[r][c] = 0;
                        if (nsz[a] != 0) {
                            nmap[nr][nc] = mx;
                        }
                    }
                }
                map = nmap;
                sz = nsz;
            }

            int ret = 0;
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    int id = map[r][c];
                    if (id <= 0) {
                        continue;
                    }
                    ret += sz[id];
                }
            }

            sb.append("#").append(t).append(" ").append(ret).append("\n");
        }
        System.out.println(sb);
    }

}