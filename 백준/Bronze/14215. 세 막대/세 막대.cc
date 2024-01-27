#include <iostream>
#include <algorithm>

using namespace std;

int main()
{

    int a,b,c;
    cin >> a >> b >> c;
    
    string res;
    int arr[3] = {a,b,c};
    sort(arr, arr+3, greater<int>());
    
    a = arr[0], b = arr[1], c = arr[2];
    
    if(a >= b + c){
        a = b + c - 1;
    }
    
    cout << (a+b+c);
    
    return 0;
}
