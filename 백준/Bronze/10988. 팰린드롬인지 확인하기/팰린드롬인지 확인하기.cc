#include <iostream>

using namespace std;

bool isPalid(string s, int a, int b){
    if(s[a] != s[b]) return false;
    if(a >= b) return true;
    return isPalid(s, a+1, b-1);
}

int main()
{
    string str;
    cin >> str;
    
    cout << isPalid(str, 0 , str.size()-1);
}
