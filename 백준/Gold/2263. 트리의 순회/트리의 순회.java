import java.io.*;
import java.util.*;

public class Main {

    static int postorder[] = new int[100020];
    static int inorder[] = new int[100020];
    static int inorderIdx[] = new int[100020];
    static int postorderIdx[] = new int[100020];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int v = Integer.parseInt(st.nextToken());
            inorder[i] = v;
            inorderIdx[v] = i;
        }
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int v = Integer.parseInt(st.nextToken());
            postorder[i] = v;
            postorderIdx[v] = i;
        }
        recur(1, N);
        System.out.println(sb);
    }

    static void recur(int s, int e) {
        int root = getRoot(s, e);
        sb.append(root).append(" ");
        if (s < inorderIdx[root]) {
            recur(s, inorderIdx[root] - 1);
        }
        if (inorderIdx[root] < e) {
            recur(inorderIdx[root] + 1, e);
        }
    }

    static int getRoot(int s, int e) {
        int mx = -1;
        int root = -1;
        for (int i = s; i <= e; i++) {
            if (postorderIdx[inorder[i]] > mx) {
                mx = postorderIdx[inorder[i]];
                root = postorder[postorderIdx[inorder[i]]];
            }
        }

        return root;
    }
}