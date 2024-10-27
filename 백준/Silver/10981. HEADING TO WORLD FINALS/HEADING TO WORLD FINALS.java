import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.*;

/**
 * 상위 2개의 대학이 세계대회 자격 대학별로 1팀만 세계대회 자격 푼문제수가 많을수록, 같으면 패널티가 작을수록 세계대회에 진출할 K개 대학 ---- 대학별로 높은 애들을 기록 그 중에서 최고를 k개 가림.
 */

public class Main {
    static class Team implements Comparable<Team> {
        String c, t; // 대학, 팀
        int s, p; // 점수, 패널티

        public Team(String c, String t, int s, int p) {
            this.c = c;
            this.t = t;
            this.s = s;
            this.p = p;
        }

        @Override
        public int compareTo(Team o) {
            if (s != o.s) {
                return o.s - s;
            }

            return p - o.p;
        }
    }

    static final int MX = 100000;

    static Team[] teams = new Team[MX + 1]; // 팀별 획득 점수
    static int[] best = new int[MX + 1]; // 대학별 현재 우승팀
    static Map<String, Integer> mapper = new HashMap<>();
    static int collageId = 1;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String c = st.nextToken();
            String t = st.nextToken();
            int s = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            // 팀 정보 생성 및 저장
            Team team = new Team(c, t, s, p);
            teams[i] = team;

            // 현재 대학의 id를 받아옴.
            int collegeId = getCollageId(c);
            // 현재 대학에 기록된 최고 팀 id
            int bestId = best[collegeId];

            // 현재 기록된 팀이 없다면
            if (bestId == 0) {
                best[collegeId] = i;
            }
            // 지금 팀이 점수가 더 높다면
            else if (teams[bestId].s < s) {
                best[collegeId] = i;
            }
            // 지금 팀이 패널티가 낮다면
            else if (teams[bestId].s == s && teams[bestId].p > p) {
                best[collegeId] = i;
            }
        }
        PriorityQueue<Team> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < MX + 1; i++) {
            if (best[i] == 0) {
                continue;
            }
            pq.add(teams[best[i]]);
        }

        for (int i = 0; i < K; i++) {
            Team team = pq.poll();
            if (team == null) {
                break;
            }
            sb.append(team.t).append("\n");
        }

        System.out.println(sb);
    }

    static int getCollageId(String c) {
        if (mapper.containsKey(c)) {
            return mapper.get(c);
        }

        mapper.put(c, collageId);
        return collageId++;
    }
}