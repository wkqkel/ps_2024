#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int val[20];
int k;
int arr[20];

const int END = 6;

void recur(int cur, int st){
    if(cur == END){
        for(int i = 0; i < END; i++){
            cout << val[arr[i]] << " ";
        }
        cout << "\n";
        return;
    }
    for(int i = st; i < k; i++){
        arr[cur] = i; 
        recur(cur+1, i+1);
    }
}

int main()
{
    while(true){
        cin >> k;
        if(k == 0) break;
        for(int i = 0; i < k; i++){
            cin >> val[i];
        }
        sort(val, val+k);
        recur(0, 0);
        cout << "\n";
    }
    
    return 0;
}