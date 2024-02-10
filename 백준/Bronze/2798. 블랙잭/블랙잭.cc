#include <iostream>
#include <algorithm>

using namespace std;

int n,m;
int arr[102];
int cards[102];
int mx = -1;

void recur(int cur, int st){
    if(cur == 3) {
        int sum = 0;
        for(int i = 0; i < 3; i++) sum += cards[arr[i]];
        if(sum <= m && mx < sum) mx = sum;
        return;
    } 
    for(int i = st; i < n; i++){
        arr[cur] = i;
        recur(cur+1, i+1);
    }
}
int main()
{
    cin >> n >> m;
    
    for(int i = 0; i <n; i++) cin >> cards[i];
    
    recur(0,0);
    
    cout << mx;
    
}
