#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;

void kan(auto arr, int st, int en){
    int len = en - st;
    
    if(len <= 1) return;

    for(int i = st; i < st + len / 3; i++){
        arr[i] = '-';
    }

    for(int i = st + len / 3; i < st + len / 3 * 2 ; i++){
        arr[i] = ' ';
    }
    
    for(int i = st + len / 3 * 2 ; i < en; i++){
        arr[i] = '-';
    }
    
    kan(arr, st, st + len / 3);
    kan(arr, st + len / 3 * 2, en);
}

int main()
{


    int n;
    
    while(cin >> n){
        int len = pow(3,n);
        char arr[len];
        kan(arr, 0, pow(3, n));
        if(len == 1) arr[0] = '-';
        for(int i =0; i < len; i++){
          cout << arr[i];
        }
        cout << "\n";
    }

    return 0;
}
