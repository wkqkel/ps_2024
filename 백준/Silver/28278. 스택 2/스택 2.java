import java.util.*;
import java.io.*;

class Stack {
    int data[] = new int[1000005];
    int sz = 0;
    
    void push(int x){
        data[sz++] = x;
    }
    int pop(){
        if(sz == 0) return -1;
        return data[--sz];
    }
    
    int size(){
        return sz;
    }
    
    boolean isEmpty(){
        return sz == 0;
    }
    
    int top(){
        if(sz == 0) return -1;
        return data[sz - 1];
    }
    
}
public class Main
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
	    
	    Stack stk = new Stack();
	    StringBuilder ret = new StringBuilder();
	    for(int i = 0; i < n; i++){
	        int x = sc.nextInt();
	        if(x == 1){
	            int v = sc.nextInt();
	            stk.push(v);
	        }
	        else if(x == 2){
	            ret.append(stk.pop()).append("\n");
	        }
	        else if(x == 3){
	            ret.append(stk.size()).append("\n");
	        }
	        else if(x ==4){
	            ret.append(stk.isEmpty() ? 1 : 0).append("\n");
	        }
	        else {
	            ret.append(stk.top()).append("\n");
	        }
	    }
	    System.out.println(ret);
	}
}