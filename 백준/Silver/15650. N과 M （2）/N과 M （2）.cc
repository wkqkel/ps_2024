#include <iostream>

using namespace std;

int n, m;

int arr[20];
void recur(int cur, int s){
    if(cur == m) {
        for(int i = 0; i < m; i++) cout << arr[i] << " ";
        cout << "\n";
        return;
    }
    for(int i = s; i <= n; i++){
        arr[cur] = i;
        recur(cur + 1, i + 1);
    }
}

int main()
{
    cin >> n >> m;
    
    recur(0, 1);

    return 0;
}