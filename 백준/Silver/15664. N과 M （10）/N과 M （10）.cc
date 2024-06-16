#include <iostream>
#include <algorithm>
#include <map>

using namespace std;

int n, m;
int arr[20];
bool ch[20];

int val[20];

map<string, bool> ch2;

void recur(int cur, int s){
    if(cur == m){
        string str = "";
        for(int i = 0; i < m; i++){
            str += to_string(val[arr[i]]) + " ";
        }
        if(ch2[str]) return;
        ch2[str] = 1;
        cout << str << "\n";
        return;
    }
    for(int i = s; i < n; i++){
        arr[cur] = i;
        recur(cur + 1, i+1);
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