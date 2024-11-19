import java.util.*;

class Solution {
    int dx[] = new int[]{1,0,-1,0};
    int dy[] = new int[]{0,-1,0,1};
    public int solution(int[][] rects, int cx, int cy, int ix, int iy) {
        int answer = 0;
   
        // 5번케이스처럼 너비가 1일때 (3,3)->(4,5)를 못감을 두 점 중 하나가 사각형 내부인지로 판단했는데, 
        // 너비가 1이라 내부임이 판단안됨.
        // 너비 2배씩
        cx *= 2;
        cy *= 2;
        ix *= 2;
        iy *= 2;
        for(int rect[]: rects){
            rect[0] *= 2;
            rect[1] *= 2;
            rect[2] *= 2;
            rect[3] *= 2;
        }
        boolean ch[][] = new boolean[200][200];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{cx,cy});
        ch[cx][cy] = true;
        
        int d = 0;
        while(!q.isEmpty()){
            int sz = q.size();
            System.out.println("d: " + d);
            while(sz-- > 0){
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                System.out.println(x + " "+ y);
                if(x == ix && y == iy){
                    return d / 2;
                }
  
                for(int dir = 0; dir < 4; dir++){
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                      
                    if(nx < 0 || nx > 200 || ny < 0 || ny > 200) continue;
                    if(!isRoad(x, y, nx, ny, rects)) continue;
                    if(isInCircle(x, y, nx, ny, rects)) continue;
                    if(ch[nx][ny]) continue;
                   
                    q.add(new int[]{nx,ny});
                    ch[nx][ny] = true;
                }
               
            }
            d++;
        }
        
        
        return answer;
    }
    
    // 출발점과 도착점이 같은 사각형의 가장자리여야 길이 될 수 있음.
    boolean isRoad(int x, int y, int nx, int ny, int[][] rects){
        for(int[] rect: rects){
            if(isRoad(x,y,rect) && isRoad(nx, ny, rect)) return true;
        }
        return false;
    }
    
    // 사각형의 가장자리인지 판단
    boolean isRoad(int x, int y, int[] rect){
        if(rect[0] <= x && x <= rect[2] && (y == rect[1] || y == rect[3])){
            return true;
        }
        if(rect[1] <= y && y <= rect[3] && (x == rect[0] || x == rect[2])){
            return true;
        }
        return false;
    }
    
    // 출발점이나 도착점 중 하나라도 사각형에 속하면, 사각형 내부로 판단
    boolean isInCircle(int x, int y, int nx, int ny, int[][] rects){
          for(int[] rect: rects){
            if(isInCircle(x,y,rect) || isInCircle(nx, ny, rect)) return true;
        }
        return false;
    }
    
    // 사각형의 내부인지 판단
    boolean isInCircle(int x, int y, int[] rect){
        return rect[0] < x && x < rect[2] && rect[1] < y && y < rect[3];
    }
}