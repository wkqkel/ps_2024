#include <iostream>

using namespace std;
int ch[10];
int main()
{

    int a, b, c;
    cin >> a >> b >> c;
    for(char c:to_string(a * b * c)){
        ch[c-'0']++;
    }
    for(int i : ch){
        cout << i << "\n";
    }
    return 0;
}
