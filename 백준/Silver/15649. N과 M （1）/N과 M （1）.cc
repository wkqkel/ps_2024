#include <iostream>

using namespace std;

int arr[10];
bool ch[10];
int n,m;

void recur(int cur){
    if(cur == m) {
        for(int i = 0; i < m; i++) cout << arr[i] << ' ';
        cout << '\n';
        return;
    }
    
    for(int i = 1; i <= n; i++){
        if(ch[i]) continue;
        arr[cur] = i;
        ch[i] = true;
        recur(cur+1);
        ch[i] = false;
    }
}
int main()
{
    cin >> n >> m;
    recur(0);

    return 0;
}
