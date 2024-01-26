#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
    int n;
    cin >> n;
    int arrX[n];
    int arrY[n];
    for(int i = 0; i < n; i++){
        int x,y;
        cin >> x >> y;
        arrX[i] = x;
        arrY[i] = y;
    }
    
    sort(arrX, arrX+n);
    sort(arrY, arrY+n);
    int res = (arrX[n -1] - arrX[0]) * (arrY[n -1] - arrY[0]);
    cout << res;
}
