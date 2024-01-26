#include <iostream>

using namespace std;

int main()
{
    int a,b,c;
    cin >> a >> b >> c;
    int sum = a + b + c;
    string res ;
    if(sum != 180){
        res = "Error";
    } else if(a== b && b == c){
        res = "Equilateral";
    } else if(a!=b && b!=c && a!=c){
        res = "Scalene";
    } else {
        res = "Isosceles";
    }
    cout << res;
    return 0;
}
