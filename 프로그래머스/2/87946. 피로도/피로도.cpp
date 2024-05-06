#include <string>
#include <vector>

using namespace std;

int arr[10];
bool ch[10];
int mx = -1;

void recur(int cur, vector<vector<int>>& dungeons, int k){
    if(cur == dungeons.size()){
        int idx = -1;
        int cur_k = k;
        for(int i = 0; i < cur; i++){
            if(dungeons[arr[i]][0] > cur_k) break;
            cur_k -= dungeons[arr[i]][1];
            mx = max(i+1, mx);
        }
        return;
    }
    for(int i = 0; i < dungeons.size(); i++){
        if(ch[i]) continue;
        ch[i] = 1;
        arr[cur] = i;
        recur(cur+1, dungeons, k);
        ch[i] = 0;
    }
}
int solution(int k, vector<vector<int>> dungeons) {
    recur(0, dungeons, k);
    return mx;
}