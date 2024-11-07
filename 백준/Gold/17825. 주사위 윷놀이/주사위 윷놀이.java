import java.io.*;
import java.util.*;


public class Main {

	static Node nodePool[] = new Node[100];
	static int arr[] = new int[10];
	static int tmp[] = new int[10];
	static int pos[] = new int[] { 0, 0, 0, 0 };
	static int ret = 0;
	static int sum = 0;
	static boolean end = false, caseend = false;

	static class Node {
		int id;
		int v;
		List<Node> next = new ArrayList<>();

		Node(int id, int v) {
			this.id = id;
			this.v = v;
		}

		@Override
		public String toString() {
			return "Node [id=" + id + ", v=" + v + ", next=" + next + "]";
		}
		
	
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));

		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 10; i++) {
			arr[i] = sc.nextInt();
		}
		setup();

		recur(0);
		System.out.println(ret);
	}

	static void recur(int cur) {
		if (cur == 10) {
			// 경우의 수
			caseend = false;
			sum = 0;
			pos = new int[] { 0, 0, 0, 0 };

			for (int t = 0; t < 10; t++) {
				int h = tmp[t];
				int moveCnt = arr[t];

				if (caseend) {
					return;
				}
				end = false;
				Node startNode = nodePool[pos[h]];

				boolean isBlue = startNode.id == 5 || startNode.id == 10 || startNode.id == 15;
//				System.out.println("--------------");
//				System.out.println("start: " + startNode);
				move(startNode, moveCnt, isBlue, h);
			}

			ret = Math.max(sum, ret);
			return;
		}

		for (int i = 0; i < 4; i++) {
			tmp[cur] = i;
			recur(cur + 1);
		}
	}

	static void move(Node cur, int moved, boolean blue, int horse) {
//		System.out.println(cur.id + " " +cur.v);
		if (cur.id == 21) { // 골인지점
			// 멈춤
			// 해당 말 현재 위치 갱신
			end = true;
			pos[horse] = cur.id;
			return;
		}
		if (moved == 0) { // 이동끝
			// 도착칸에 다른 말이 없다먄 해당 케이스 불가능
			for (int id : pos) {
				if (cur.id == id && cur.id != 21) {
					end = true;
					caseend = true;
					return;
				}
			}
			// 현재 위치 값 더해주기
			sum += cur.v;
			// 해당 말 현재 위치 갱신
			pos[horse] = cur.id;
			return;
		}

		if (!end && blue)
			move(cur.next.get(1), moved - 1, false, horse);
		else if (!end)
			move(cur.next.get(0), moved - 1, false, horse);
	}

	static void setup() {
		int values[] = new int[] { // 시작 i
				0, 2, 4, 6, 8, 10, // 0
				12, 14, 16, 18, 20, // 6
				22, 24, 26, 28, 30, // 11
				32, 34, 36, 38, 40, 0, // 16
				13, 16, 19, 25, // 22
				22, 24, // 26
				28, 27, 26, // 28
				30, 35 // 31
		};

		for (int i = 0; i < values.length; i++) {
			Node node = new Node(i, values[i]);
			nodePool[i] = node;
		}

		Node prev = null;
		for (int i = 0; i < 22; i++) {
			Node node = nodePool[i];
			if (prev != null) {
				prev.next.add(node);
			}
			prev = node;
		}
		prev = nodePool[5]; // v 10
		for (int i : new int[] { 22, 23, 24, 25 }) {
			Node node = nodePool[i];
			prev.next.add(node);
			prev = node;
		}

		prev = nodePool[10]; // v 20
		for (int i : new int[] { 26, 27, 25 }) {
			Node node = nodePool[i];
			prev.next.add(node);
			prev = node;
		}

		prev = nodePool[15]; // v 30
		for (int i : new int[] { 28, 29, 30, 25 }) {
			Node node = nodePool[i];
			prev.next.add(node);
			prev = node;
		}

		prev = nodePool[25]; // v 25
		for (int i : new int[] { 31, 32, 20 }) {
			Node node = nodePool[i];
			prev.next.add(node);
			prev = node;
		}
	}

}