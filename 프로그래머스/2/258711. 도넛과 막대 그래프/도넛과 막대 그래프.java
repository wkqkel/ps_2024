class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int maxNode = 0;
    
        
        // 최대 노드 번호 찾기
        for(int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        boolean[] hasNode = new boolean[maxNode + 1];
        for(int[] edge : edges) {
            hasNode[edge[0]] = true;
            hasNode[edge[1]] = true;
        }
        
        int[] indegree = new int[maxNode + 1];
        int[] outdegree = new int[maxNode + 1];
        
        // 진입, 진출 차수 계산
        for(int[] edge : edges) {
            outdegree[edge[0]]++;
            indegree[edge[1]]++;
        }
        
        // 생성된 정점 찾기 (진출 >= 2, 진입 = 0)
        for(int i = 1; i <= maxNode; i++) {
            if(outdegree[i] >= 2 && indegree[i] == 0) {
                answer[0] = i;
                break;
            }
        }
        
        // 생성된 정점 제외 진입, 진출 차수 계산
        for(int[] edge : edges) {
            if(edge[0] == answer[0]){
                indegree[edge[1]]--;
            }
        }
        
        // 각 그래프의 개수 계산
        for(int i = 1; i <= maxNode; i++) {
            if(i == answer[0] || !hasNode[i]) continue;  // 생성된 정점 제외
            
            // 8자 그래프: 진입 2개, 진출 2개인 정점이 있음
            if(indegree[i] == 2 && outdegree[i] == 2) {
                answer[3]++;
            }
            // 막대 그래프: 진출이 0인 정점이 있음
            else if(outdegree[i] == 0) {
                answer[2]++;
            }
        }
        
        // 도넛 그래프 = 전체 그래프 수 - (막대 + 8자)
        answer[1] = outdegree[answer[0]] - (answer[2] + answer[3]);
        
        return answer;
    }
}