#include <iostream>

using namespace std;

long long fac(int a){
    if(a <= 1){
        return 1;
    }
    return fac(a - 1) * a;
}
int main()
{
    int n;
    cin >> n;

    cout << fac(n);


    return 0;
}
