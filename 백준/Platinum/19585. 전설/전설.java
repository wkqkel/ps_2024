import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Trie color = new Trie();
        Set<String> nameSet = new HashSet<>();  // Trie 대신 HashSet 사용
        
        for (int i = 0; i < N; i++) {
            color.insert(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            nameSet.add(br.readLine());  // 닉네임은 HashSet에 저장
        }
        
        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            String team = br.readLine();
            ArrayList<Integer> colorPoints = new ArrayList<>();
            
            // 가능한 모든 색상 끝점 찾기
            Node cur = color.root;
            for (int j = 0; j < team.length() && cur != null; j++) {
                cur = cur.children[Trie.c2i(team.charAt(j))];
                if (cur != null && cur.isLeaf) {
                    colorPoints.add(j);
                }
            }
            
            // 찾은 색상 끝점에 대해 닉네임 확인
            boolean flag = false;
            for (int point : colorPoints) {
                if (nameSet.contains(team.substring(point + 1))) {
                    flag = true;
                    break;
                }
            }
            
            sb.append(flag ? "Yes" : "No").append("\n");
        }
        System.out.println(sb);
    }
    
  
}

class Node {
    Node[] children = new Node[26];
    boolean isLeaf;
}

class Trie {
    Node root;
    Trie() {
        root = new Node();
    }
    void insert(String str) {
        Node cur = root;
        for (char c : str.toCharArray()) {
            if (cur.children[c2i(c)] == null) {
                cur.children[c2i(c)] = new Node();
            }
            cur = cur.children[c2i(c)];
        }
        cur.isLeaf = true;
    }
    
    static int c2i(char c) {
        return c - 'a';
    }
}