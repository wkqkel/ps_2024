import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static BufferedReader br;
    static StringTokenizer st;
    static int map[][] = new int[12][12];

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            ArrayList<Person> people = new ArrayList<>();
            ArrayList<Stair> stairs = new ArrayList<>();

            int seq = 0;
            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] == 1) {
                        people.add(new Person(seq++, r, c));
                    }
                    if (map[r][c] >= 2) {
                        stairs.add(new Stair(map[r][c], r, c));
                    }
                }
            }

            int mn = Integer.MAX_VALUE;

            for (int k = 0; k < 1 << seq; k++) {
                boolean[] arrived = new boolean[seq];
                Stair stair1 = stairs.get(0).clear();
                Stair stair2 = stairs.get(1).clear();
                PriorityQueue<Person> waits1 = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
                PriorityQueue<Person> waits2 = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
                for (int j = 0; j < seq; j++) {
                    if ((k & 1 << j) != 0) {
                        waits1.add(people.get(j).setDist(stair1.r, stair1.c));
                    } else {
                        waits2.add(people.get(j).setDist(stair2.r, stair2.c));
                    }
                }

                int time = 0;
                while (true) {
                    time++;

                    ArrayList<Person> out1 = stair1.out();
                    ArrayList<Person> out2 = stair2.out();

                    for (Person p : out1) {
                        arrived[p.id] = true;
                    }

                    for (Person p : out2) {
                        arrived[p.id] = true;
                    }

                    boolean allArrived = true;
                    for (int j = 0; j < seq; j++) {
                        if (!arrived[j]) {
                            allArrived = false;
                        }
                    }
                    if (allArrived) {
                        break;
                    }

                    while (!stair1.isFull() && !waits1.isEmpty() && waits1.peek().dist <= time) {
                        stair1.enter(waits1.poll());
                    }

                    while (!stair2.isFull() && !waits2.isEmpty() && waits2.peek().dist <= time) {
                        stair2.enter(waits2.poll());
                    }

                }
                mn = Math.min(mn, time + 1);

            }

            sb.append("#").append(t).append(" ").append(mn).append("\n");

        }
        System.out.println(sb);
    }

    static class Person {

        int id, r, c;
        int dist;

        Person(int id, int r, int c) {
            this.id = id;
            this.r = r;
            this.c = c;
        }

        Person setDist(int r, int c) {
            this.dist = Math.abs(this.r - r) + Math.abs(this.c - c);
            return this;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", r=" + r +
                    ", c=" + c +
                    ", dist=" + dist +
                    '}';
        }
    }

    static class Stair {

        int occupancy = 0;
        int capacity;
        int r, c;
        ArrayList<Person>[] stairs;

        Stair(int capacity, int r, int c) {
            this.capacity = capacity;
            this.r = r;
            this.c = c;
            this.stairs = new ArrayList[capacity];

            for (int i = 0; i < capacity; i++) {
                this.stairs[i] = new ArrayList<>();
            }
        }

        void enter(Person person) {
            this.stairs[this.capacity - 1].add(person);
            this.occupancy++;
        }

        ArrayList<Person> out() {
            ArrayList<Person> ret = new ArrayList<>();
            for (Person op : stairs[0]) {
                ret.add(op);
            }

            ArrayList<Person>[] newStairs = new ArrayList[capacity];
            for (int i = 0; i < capacity; i++) {
                newStairs[i] = new ArrayList<>();
            }
            System.arraycopy(stairs, 1, newStairs, 0, capacity - 1);
            this.stairs = newStairs;
            this.occupancy -= ret.size();

            return ret;
        }

        boolean isFull() {
            return this.occupancy >= 3;
        }

        Stair clear() {
            return new Stair(capacity, r, c);
        }
    }
}
