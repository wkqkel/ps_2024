#include <iostream>

using namespace std;

int main()
{
    string s;

    while(true){
       s ="";
       getline(cin, s);
       if(s.empty()) break;
       cout << s << '\n';
    }
}
