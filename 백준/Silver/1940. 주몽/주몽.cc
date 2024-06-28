#include <iostream>
#include <map>
using namespace std;

map<int, int> arr;

/**
입력:
9
10
1 2 3 4 5 6 7 8 9
출력:
5
정답:
4
*/
int main()
{
    int n,m;
    cin >> n >> m;
    int ret = 0;
    
    for(int i = 0; i < n; i++){
        int v;
        cin >> v;
        if(arr[m - v]) {
            ret++;
            arr[m - v]--;
        } else {
            arr[v]++;
        }
    }
    
    cout << ret;
    return 0;
}