import java.util.*;

class Solution {
    
    public int solution(int[][] game_board, int[][] table) {
    
    
        
        List<Shape> emptys = getShapes(game_board, 0);
        List<Shape> blocks = getShapes(table, 1);
        boolean[] ch = new boolean[blocks.size()];
        int ret = 0;
   
        for(int i = 0; i < emptys.size(); i++){
            Shape empty = emptys.get(i);
            jp: for(int j = 0; j < blocks.size(); j++){
                Shape block = blocks.get(j);
                
                if(ch[j]) continue;
                for(int dir = 0; dir < 4; dir++){
                     if(block.equals(empty)){
                        ret += block.getSize();
                        ch[j] = true;
                        break jp;
                    } else {
                        block.rotate();
                    }
                }
            }
        }
        
        return ret;
    }
    
    List<Shape> getShapes(int[][] board, int type){
        boolean[][] ch = new boolean[52][52];
        List<Shape> ret = new ArrayList<>();
   
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(ch[i][j] || board[i][j] != type) continue;
                ret.add(getShape(board,i,j, type, ch));
            }
        }
        return ret;
    }
    
    Shape getShape(int[][] board, int x, int y, int type, boolean[][] ch){
        Shape shape = new Shape();
        int[] dx = new int[]{1,0,-1,0};
        int[] dy = new int[]{0,-1,0,1};  
        int N = board.length;
        int M = board[0].length;
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(x,y));
        ch[x][y] = true;
        
        shape.add(new Pair(0,0));
        
        while(!q.isEmpty()){
            Pair cur = q.poll();
            
            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(board[nx][ny] != type || ch[nx][ny]) continue;
                q.add(new Pair(nx, ny));
                ch[nx][ny] = true;
                shape.add(new Pair(nx - x, ny - y));
            }
        }
        shape.sort();
        return shape;
    }
    
   
}
class Pair implements Comparable<Pair> {
    int x, y;
    
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int compareTo(Pair o){
        if (this.x == o.x) {
            return this.y - o.y;
        }
        
        return this.x - o.x;
    }
    
    boolean equals(Pair o){
        return x == o.x && y == o.y;
    }
    
    
    public String toString(){
        return String.format("(x:%d, y:%d)", x, y);
    }
}
class Shape {
    List<Pair> list = new ArrayList<>();
    
    void add(Pair pair){
        list.add(pair);
    }
    
    void rotate(){
        // (x, y) -> (y, -x)
        for(Pair p : list){
            int tmp = p.x;
            p.x = p.y;
            p.y = -tmp; 
        }
        // 정렬
        sort();
        // 위치조정
        int cx = list.get(0).x;
        int cy = list.get(0).y;
        for(Pair p : list){
            p.x -= cx;
            p.y -= cy;
        }
    }
    
    int getSize(){
        return this.list.size();
    }
    
    Pair get(int idx){
        return list.get(idx);
    }
    
    void sort(){
        Collections.sort(list);
    }
    
    boolean equals(Shape o){
        if(getSize() != o.getSize()) {
            return false;
        }
        
        for(int i = 0; i < getSize(); i++){
            Pair p1 = list.get(i);
            Pair p2 = o.get(i);
            if(!p1.equals(p2)) return false;
        }
        
        return true;
    }
    
}