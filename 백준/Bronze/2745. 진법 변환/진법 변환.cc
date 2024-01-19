#include <iostream>

using namespace std;

int main()
{
    string str;
    int b;
    cin >> str >> b;
    
    int res = 0;
    for(int i = 0; i < str.size(); i++){
        int delta = 1;
        for(int j = 0; j < str.size() -i -1; j++) {
            delta *= b;
        }
        int v = (str[i] - '0');
        if(v > 10) v -= 7;
        res += v * delta;
    }
    
    cout << res;

    return 0;
}
