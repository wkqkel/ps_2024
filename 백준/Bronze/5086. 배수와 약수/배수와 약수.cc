#include <iostream>

using namespace std;

int main()
{
    int a, b;
    cin >> a >> b;
    while(a != 0 && b != 0){
        string res;
        if(b % a == 0){
            res = "factor";
        } else if(a%b ==0){
            res = "multiple";
        } else {
            res = "neither";
        }
        cout << res << '\n';
        cin >> a >> b;
    }
}
