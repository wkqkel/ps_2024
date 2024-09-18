import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int arr[] = new int[N];
      for(int i = 0; i < N; i++){
        arr[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(arr);
      int res[] = new int[2];
      int mn = Integer.MAX_VALUE;
      for(int i = 0; i < N; i++){
        int s = i + 1;
        int e = arr.length - 1;

        while(s <= e){
          int mid = (s + e) / 2;
          int sum = Math.abs(arr[i] + arr[mid]);
          if(mn > sum){
            mn = sum;
            res[0] = arr[i];
            res[1] = arr[mid];
          }
          if(arr[mid] < -arr[i]){
            s = mid + 1;
          } else {
            e = mid - 1;
          }
        }
        
      }
      
      Arrays.sort(res);
    
      System.out.println(res[0] + " " + res[1]);
    }
    
    
  
}