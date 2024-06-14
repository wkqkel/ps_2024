#include <iostream>

using namespace std;


/**
a,b,c를 받아서, 
A를 B번 곱한 수에서 C로 나눈 나머지를 
반환하는 재귀함수

*/
#define ll long long

ll recur(ll a, ll b, ll c){
    if(b == 1) return a % c;
    ll val = recur(a , b / 2, c);
    val = val * val % c;
    if(b % 2 == 0) return val % c;
    return val * a % c;
}

int main()
{
    ll a, b, c;
    cin >> a >> b >> c;
    
    cout << recur(a, b, c);

    return 0;
}
