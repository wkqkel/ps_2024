class Solution {
    int R, C;
    int[][] pos = new int[6][2];
    int[] dr = new int[]{0,1,0,-1};
    int[] dc = new int[]{1,0,-1,0};
    int[][] map;
    boolean ch[][][] = new boolean[2][4][4];
    
    int ret = Integer.MAX_VALUE;
    public int solution(int[][] maze) {
        R = maze.length;
        C = maze[0].length;
        map = maze;
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                pos[maze[r][c]] = new int[]{r,c};
            }
        }
        ch[0][pos[1][0]][pos[1][1]] = true;
        ch[1][pos[2][0]][pos[2][1]] = true;
        dfs(0, pos[1][0], pos[1][1], pos[2][0], pos[2][1], ch);
        
        if(ret == Integer.MAX_VALUE){
            return 0;
        }
        
        return ret;
    }
    
    void dfs(int cur, int rr, int rc, int br, int bc, boolean ch[][][]){
        if(map[rr][rc] == 3 && map[br][bc] == 4){
            ret = Math.min(cur, ret);
            return; 
        }
        // 1. 빨강이 도착 - 파랑만 움직임
        if(map[rr][rc] == 3){
            for(int bd = 0; bd < 4; bd++){
                int nbr = br + dr[bd];
                int nbc = bc + dc[bd];   
                // 벽이나 격자판으로 움직일 수 없음.
                if(overMap(nbr, nbc) || map[nbr][nbc] == 5) continue;
                // 자신이 방문했던 칸으로 움직일 수 없음.
                if(ch[1][nbr][nbc]) continue;
                if(nbr == rr && nbc == rc) continue;
                ch[1][nbr][nbc] = true;
                dfs(cur+1, rr,rc, nbr, nbc, ch);
                ch[1][nbr][nbc] = false;
            }
        }
        // 2. 파랑이 도착 - 빨강만 움직임
        else if(map[br][bc] == 4){
            for(int rd = 0; rd < 4; rd++){
                int nrr = rr + dr[rd];
                int nrc = rc + dc[rd];   
                // 벽이나 격자판으로 움직일 수 없음.
                if(overMap(nrr, nrc) || map[nrr][nrc] == 5) continue;
                // 자신이 방문했던 칸으로 움직일 수 없음.
                if(ch[0][nrr][nrc]) continue;
                if(nrr == br && nrc == bc) continue;
                ch[0][nrr][nrc] = true;
                dfs(cur+1, nrr,nrc, br, bc, ch);
                ch[0][nrr][nrc] = false;
            }
        }
        // 둘 다
        else {
            for(int rd = 0; rd < 4; rd++){
                int nrr = rr + dr[rd];
                int nrc = rc + dc[rd];
                if(overMap(nrr, nrc) || map[nrr][nrc] == 5) continue;
                if(ch[0][nrr][nrc]) continue;
                for(int bd = 0; bd < 4; bd++){
                    int nbr = br + dr[bd];
                    int nbc = bc + dc[bd];   
                    // 벽이나 격자판으로 움직일 수 없음.
                    if(overMap(nbr, nbc) || map[nbr][nbc] == 5) continue;
                    // 자신이 방문했던 칸으로 움직일 수 없음.
                    if(ch[1][nbr][nbc]) continue;
                    // 동시에 같은 칸으로 움직일 수 없음.
                    if(nrr == nbr && nrc == nbc) continue;
                    // 수레끼리 자리를 바꿀 수없음
                    if(nrr == br && nrc == bc && nbr == rr && nbc == rc) continue;
                    ch[0][nrr][nrc] = true;
                    ch[1][nbr][nbc] = true;
                    dfs(cur+1, nrr,nrc, nbr, nbc, ch);
                    ch[0][nrr][nrc] = false;
                    ch[1][nbr][nbc] = false;
                }
            }
        }
       
    }
    
    boolean overMap(int r, int c){
        return r < 0 || r >= R || c < 0 || c >= C;
    }
}