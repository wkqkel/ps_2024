#include <iostream>

using namespace std;

int chess[] = {1,1,2,2,2,8};
int main()
{
    for(int i = 0; i < 6; i++){
        int v;
        cin >> v;
        cout << chess[i] - v << ' ';
    }
}
