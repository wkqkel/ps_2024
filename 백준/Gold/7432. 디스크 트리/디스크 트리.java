import java.io.*;
import java.util.*;

public class Main {

    static class Node {

        Map<String, Node> children = new TreeMap<>();
    }

    static Node root = new Node();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] dirs = br.readLine().split("\\\\");
            insert(dirs);
        }

        print(root, 0);
        System.out.print(sb);
    }

    static void insert(String[] dirs) {
        Node current = root;
        for (String dir : dirs) {
            current.children.putIfAbsent(dir, new Node());
            current = current.children.get(dir);
        }
    }

    static void print(Node node, int depth) {
        for (Map.Entry<String, Node> entry : node.children.entrySet()) {
            sb.append(" ".repeat(depth)).append(entry.getKey()).append("\n");
            print(entry.getValue(), depth + 1);
        }
    }
}