#include <iostream>
#include <algorithm>

using namespace std;

int n, m;
int arr[20];
bool ch[20];

int val[20];

void recur(int cur, int s){
    if(cur == m){
        for(int i = 0; i < m; i++){
            cout << val[arr[i]] << " ";
        }
        cout << "\n";
        return;
    }
    int tmp = 0;
    for(int i = s; i < n; i++){
        if(tmp == val[i]) continue;
        arr[cur] = i;
        tmp = val[i];
        recur(cur + 1, i);
    }
}
int main()
{
    cin >> n >> m;
    
    for(int i = 0; i < n; i++){
        cin >> val[i];
    }

    sort(val, val + n);
    
    recur(0, 0);
 
    return 0;
}