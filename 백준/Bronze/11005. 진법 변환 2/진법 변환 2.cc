#include <iostream>

using namespace std;

int main()
{
    int n, b;
    cin >> n >> b;
    string str = "";
    
    while(n >= 1){
        int rest = n % b;
        n /= b;
        if(rest >= 10){
          rest += 'A' - 10;  
        } else {
          rest += '0';
        }
        str = (char)rest + str;
    }
    
    cout << str;

    return 0;
}
