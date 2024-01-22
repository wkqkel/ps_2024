#include <iostream>

using namespace std;

int main()
{
    int n;
    cin >> n;
    for(int i = 0; i < n; i++){
        int v;
        cin >> v;
        int qs, ds, ns, ps;
        qs = v / 25;
        v %= 25;
        ds = v / 10;
        v %= 10;
        ns = v / 5;
        v %= 5;
        ps = v / 1;
        cout << qs << ' ' << ds << ' ' << ns << ' ' << ps << '\n';
    }
}
