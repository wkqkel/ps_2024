#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
    while(true){
        int a,b,c;
        cin >> a >> b >> c;
        if(!a && !b && !c) break;
        
        string res;
        int arr[3] = {a,b,c};
        sort(arr, arr+3, greater<int>());
        
        a = arr[0], b = arr[1], c = arr[2];
        
        if(a >= b + c){
            res = "Invalid";
        } else if(a==b&&b==c){
            res = "Equilateral";
        } else if (a!=b && b!= c && a!=c){
            res = "Scalene";
        } else {
            res = "Isosceles";
        }
        
        cout << res << '\n';
    }
    
    return 0;
}
