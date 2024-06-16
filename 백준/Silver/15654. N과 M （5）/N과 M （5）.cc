#include <iostream>
#include <algorithm>

using namespace std;

int n, m;
int arr[20];

int val[20];
bool ch[20];

void recur(int cur){
    if(cur == m){
        for(int i = 0; i < m; i++) cout << val[arr[i]] << " ";
        cout << "\n";
        return;
    }
    for(int i = 0; i < n; i++){
        if(ch[i]) continue;
        ch[i] = 1;
        arr[cur] = i;
        recur(cur + 1);
        ch[i] = 0;
    }
}
int main()
{
    cin >> n >> m;
    
    for(int i = 0; i < n; i++){
        cin >> val[i];
    }
    sort(val, val+n);
    
    recur(0);

    return 0;
}