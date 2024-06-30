#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

/**
n과 m이 최대 20000 = 2e4
정렬한뒤 A돌면서, B의 lowerBound찾아서 빼주기
*/
int main()
{
    int t;
    cin >> t;
    while(t--){
        int n, m;
        cin >> n >> m;
        vector<int> A, B;
        for(int i = 0; i < n; i++) {
            int v;
            cin >> v;
            A.push_back(v);
        } 
        for(int i = 0; i < m; i++) {
            int v;
            cin >> v;
            B.push_back(v);
        } 
        sort(A.begin(),A.end());
        sort(B.begin(),B.end());
        int ret = 0;
        for(int i : A){
            ret += lower_bound(B.begin(), B.end(), i) - B.begin();
        }
        cout << ret << "\n";
    }

    return 0;
}