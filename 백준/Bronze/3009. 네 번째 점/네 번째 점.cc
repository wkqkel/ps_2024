#include <iostream>
#include <map>

using namespace std;

map<int, int> chX;
map<int, int> chY;
int main()
{
    for(int i = 0; i < 3; i++){
        int x,y;
        cin >> x >> y;
        chX[x]++;
        chY[y]++;
    }
    for(auto iter: chX){
        if(iter.second == 1) cout << iter.first;
    }
    cout << ' ';
    for(auto iter: chY){
        if(iter.second == 1) cout << iter.first;
    }
}
