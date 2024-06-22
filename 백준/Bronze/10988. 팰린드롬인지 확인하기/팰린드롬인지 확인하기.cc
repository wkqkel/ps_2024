#include <iostream>

using namespace std;

bool is_palidrome(string& str, int s, int e){
    if(s >= e) return true;
    if(str[s] != str[e]) return false;
    return is_palidrome(str, s+1, e-1);
}
int main()
{
    string str;
    cin >> str;
    
    cout << is_palidrome(str, 0, str.size() - 1);
    return 0;
}