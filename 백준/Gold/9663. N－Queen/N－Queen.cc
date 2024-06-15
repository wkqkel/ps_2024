#include <iostream>
#include <map>

using namespace std;

int n;

int res = 0;

bool ch_x[20];
bool ch_y[20];
bool ch_a[100];
map<int, bool> ch_b;

void recur(int x){
    if(x == n){
        res++;
        return;
    } 
    for(int y = 0; y < n; y++){
        // ch_b 0,4 = 1,5, = 2,6 => x+i
        // ch_a 0,4 = 1,3 = 2,2 => x-i
        if(ch_x[x] || ch_y[y] || ch_a[x+y] || ch_b[x-y]) continue;
        ch_x[x] = 1;
        ch_y[y] = 1;
        ch_a[x+y] = 1;
        ch_b[x-y] = 1;
        recur(x+1);
        ch_x[x] = 0;
        ch_y[y] = 0;
        ch_a[x+y] = 0;
        ch_b[x-y] = 0;
    }
}

int main()
{
    cin >> n;
    
    recur(0);
    
    cout << res;
    return 0;
}
