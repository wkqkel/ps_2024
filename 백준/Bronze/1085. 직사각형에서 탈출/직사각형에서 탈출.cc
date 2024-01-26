#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
    int x,y,w,h;
    cin >> x >> y >> w >> h;
    int mx = min({abs(0 - x), abs(w - x)});
    int my = min({abs(0 - y), abs(h - y)});
    cout << min({mx,my});
}
