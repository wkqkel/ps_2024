#include <iostream>
#include <list>
using namespace std;

int main()
{
    int t;
    cin >> t;
    while(t--){
        string str;
        cin >> str;
        
        list<char> li;
        
        auto cursor = li.begin();
        for(char c: str){
            if(c == '<'){
                if(cursor == li.begin()) continue;
                cursor--;
            }
            else if(c == '>'){
                if(cursor == li.end()) continue;
                cursor++;
            }
            else if(c == '-'){
                if(cursor == li.begin()) continue;
                cursor--;
                cursor = li.erase(cursor);
            }
            else {
                li.insert(cursor, c);
            }
        }
        for(char c: li){
            cout << c;
        }
        cout << "\n";
    }
 
    return 0;
}
