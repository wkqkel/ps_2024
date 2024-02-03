#include <iostream>

using namespace std;

int months[13] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
string days[7] = {"SUN","MON", "TUE","WED","THU","FRI","SAT"};;

int main()
{   
    int m, d;
    cin >> m >> d;
    for(int i = 0; i<m;i++){
        d+= months[i];
    }

    cout << days[d % 7];
    return 0;
}
