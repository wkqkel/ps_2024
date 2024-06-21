#include <iostream>
#include <algorithm>

using namespace std;

int arr[9];
int tmp[9];

bool flag; 
// 최대 502
void recur(int cur,int st){
    if(flag) return; 
    if(cur == 7){
        int sum = 0;
        for(int i = 0; i < 7; i++) sum += arr[tmp[i]];
        if(sum == 100) {
            flag = true;
            for(int i = 0; i < 7; i++) cout << arr[tmp[i]] << "\n";
        }
        return;
    }
    for(int i = st; i < 9; i++){
        tmp[cur] = i;
        recur(cur+1, i + 1);
    }
}
int main()
{
    for(int i = 0; i < 9; i++){
        cin >> arr[i];
    }
    sort(arr, arr+9);
    
    recur(0,0);
    
    return 0;
}