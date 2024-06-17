#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/**
# Boj 15686 치킨거리
## 문제이해
- N*N도시
- 빈칸,집,가게 = {0,1,2}
- 치킨거리: 현재 집에서 가장 가까운 가게 거리
- 도시치킨거리: 각집의 치킨거리의 합
- M은 살아남을 치킨집 개수

## 설계
1. 치킨집 고르는 경우의 수 구하기
- 치킨집 최대 13개 중에서 M개 고르기
- 최대 13C6 

2. 각 집의 치킨거리 구하기
- 살아남은 치킨 -> 집 이중 돌면서 최소 치킨거리 구하기
- 이 때 집마다 돌면서, 최소 치킨거리 갱신
- 13 * 13
3. 도시치킨거리 최소합 갱신
- 집마다 돌면서 sum 구하기
- sum의 min값 구하기

---
집의 개수는 2N을 넘지않음 => dist의 MX 102 설정;
치킨집과 M의 최대 개수는 13 => arr의 MX 20설정
*/


int n, m;

vector<pair<int, int>> stores;
vector<pair<int, int>> houses;
int dists[102];
vector<vector<int>> tests;
vector<int> arr;

void recur(int cur, int st){
    if(cur == m){
        tests.push_back(arr);
        return;
    }
    for(int i = st; i < stores.size(); i++){
        arr.push_back(i);
        recur(cur+1, i+1);
        arr.pop_back();
    }
}

int get_dist(auto& x, auto& y){
    return abs(x.first - y.first) + abs(x.second - y.second);
}

int main()
{
    cin >> n >> m;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            int v;
            cin >> v;
            if(v == 2) stores.push_back({i,j});
            else if(v == 1) houses.push_back({i,j});
        }
    }
    
    recur(0, 0);

    int mn = 1e9;
    // 1. 살아남은 가게 경우의수
    for(auto t : tests){
        for(int i = 0; i < 102; i++){
            dists[i] = 1e9;
        }
        // 2-1. 먼저 살아남은 치킨집 돌고
        for(int alive_idx: t){
            auto s = stores[alive_idx];
             // 2-2. 집 돌면서 최솟값 갱신
            for(int i = 0; i < houses.size(); i++){
                auto h = houses[i];
                dists[i] = min(dists[i], get_dist(h, s));
            }
        }
        // 3. 도시거리 구해서, 최솟값 갱신
        int sum = 0;
        for(int i = 0; i < houses.size();i++){
            sum += dists[i];
        }
        
        mn = min(sum, mn);
    }
    
    cout << mn;

    return 0;
}