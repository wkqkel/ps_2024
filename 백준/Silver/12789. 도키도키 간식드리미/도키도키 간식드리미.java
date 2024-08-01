import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
   
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<Integer> stk = new Stack<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            q.add(sc.nextInt());
        }
        int cur = 1;
        while (true) {
            if(!q.isEmpty() && q.peek() == cur) {
                q.poll();
                cur++;
            }
            else if(!stk.isEmpty() && stk.peek() == cur) {
                stk.pop();
                cur++;
            }
            else if(!q.isEmpty()) {
                stk.push(q.poll());
            }
            else break;
        }
      
        if(cur >= n) System.out.println("Nice");
        else System.out.println("Sad");
    }
}