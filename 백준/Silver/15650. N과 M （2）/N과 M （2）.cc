#include <iostream>

using namespace std;

int arr[10];
int ch[10];
int n,m;

void recur(int cur, int st){
    if(cur == m){
        for(int i = 0; i < m; i++) cout << arr[i] << ' '; 
        cout << '\n';
        return;
    }
    for(int i = st; i <= n; i++){
        arr[cur] = i;
        recur(cur+1, i+ 1);
    }
}
int main()
{
    cin >> n >> m;
    recur(0,1);

    return 0;
}
