#include <iostream>

using namespace std;

int main()
{
    string arr[15][5];

    for(int i = 0; i < 5; i++){
        string str;
        getline(cin, str);
        for(int j = 0; j < str.size(); j++){
          arr[j][i] = str[j];
        }
    }
    for(int i = 0; i< 15; i++){
        for(int j =0; j < 5; j++){
            cout << arr[i][j];
        }
    }
}
