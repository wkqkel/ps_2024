#include <iostream>

using namespace std;

int n, m;

int arr[10];
bool ch[10];
    
void recur(int cur){
    if(cur == m){
        for(int i = 0; i < m; i++) cout << arr[i] << " ";
        cout << '\n';
    }
    for(int i = 1; i <= n; i++){
        if(ch[i]) continue;
        ch[i] = 1;
        arr[cur] = i;
        recur(cur+1);
        ch[i] = 0;
        arr[cur] = 0;
    }
}
int main()
{
    cin >> n >> m;
    
    recur(0);
    return 0;
}