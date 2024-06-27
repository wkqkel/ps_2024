#include <iostream>

using namespace std;


int n, m;
int ret = -1e9;
int psum[100020];
int main()
{
    cin >> n >> m;
    for(int i = 1; i <= n; i++) {
        int v;
        cin >> v;
        psum[i] = psum[i-1] + v;
    }
    
    for(int i = m; i <= n; i++){
        ret = max(ret, psum[i] - psum[i-m]);
    }
    cout << ret;
    return 0;
}