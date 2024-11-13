import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int rtype[] = new int[]{0, 1, 3, 2};

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        Board green = new Board();
        Board blue = new Board();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            green.setTile(t, c);
            blue.setTile(rtype[t], r);
        }

        System.out.println(green.score + blue.score);
        System.out.println(green.getTilesCnt() + blue.getTilesCnt());
    }
}

class Board {
    int[][] tiles;
    int score;

    Board() {
        tiles = new int[7][5];
        score = 0;

        // tiles[6] = new int[]{1,1,1,1,4};
        Arrays.fill(tiles[6], 1);
    }

    void setTile(int type, int c) {
        for (int r = 2; r < tiles.length; r++) {
            int[] check = tiles[r];
            // 막힌 곳 위에 설치
            if (type == 1 && check[c] == 1) {
                tiles[r - 1][c] = 1;
                tiles[r - 1][4]++;
                break;
            } else if (type == 2 && (check[c] == 1 || check[c + 1] == 1)) {
                tiles[r - 1][c] = 1;
                tiles[r - 1][c + 1] = 1;
                tiles[r - 1][4] += 2;
                break;
            } else if (type == 3 && check[c] == 1) {
                tiles[r - 1][c] = 1;
                tiles[r - 1][4]++;
                tiles[r - 2][c] = 1;
                tiles[r - 2][4]++;
                break;
            }
        }
        // 꽉 찬 줄 있으면 그만큼 내리기
        checkRow();
    }

    private void checkRow() {
        // 진한 색 영역
        for (int r = 5; r >= 2; r--) { // 아래에서부터 체크
            // (0,1영역은 어차피 비어있어서 확인 안해도됨)
            if (tiles[r][4] == 4) {
                for (int r2 = r; r2 > 0; r2--) {
                    tiles[r2] = tiles[r2 - 1]; // 위 배열 통쨰로 내리기
                }
                tiles[0] = new int[]{0, 0, 0, 0, 0};
                score++;
                r++;  // 자기 자신이 다시 찼을 수 있으므로 재검사하게끔
            }
        }

        // 연한 영역
        for (int r = 1; r >= 0; r--) { // 역시 아래에서부터
            if (tiles[1][4] > 0) {
                for (int r2 = 5; r2 > 0; r2--) {
                    tiles[r2] = tiles[r2 - 1];
                }
                tiles[0] = new int[]{0, 0, 0, 0, 0};
            }
        }
    }

    int getTilesCnt() {
        int cnt = 0;
        for (int r = 2; r <= 5; r++) {
            cnt += tiles[r][4];
        }
        return cnt;
    }
}