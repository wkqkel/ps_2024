#include <iostream>

using namespace std;

int ch[26];

int main()
{
    string str;
    cin >> str;
    for(char c: str){
        ch[c - 'a']++;
    }
    for(int i : ch){
        cout << i << " ";
    }
    

    return 0;
}
