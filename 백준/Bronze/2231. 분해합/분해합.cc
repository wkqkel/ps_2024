#include <iostream>
#include <algorithm>

using namespace std;

int n;
int arr[10000000];




int main()
{
    cin >> n;
 
    for(int i = 1; i< 10000000;i++){
        int c = i;
        int sum = i;
        while(c>=1){
            int rest =  c % 10;
            c /= 10;
            sum += rest;
        }
        if(!arr[sum]) arr[sum] = i;
    }
    cout << arr[n] || 0;
}
