#include <iostream>
#include <algorithm>

using namespace std;

int n, k;
int arr[10020];
int cnt = 0;

void bubble_sort(){
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n - i - 1; j++){
            if(arr[j] > arr[j+1]) {
                swap(arr[j], arr[j+1]);
                cnt++;
                if(cnt == k){
                    for(int m = 0; m < n; m++){
                        cout << arr[m] << " ";
                    }
                }
            };
        }
    }
}


int main()
{
    cin >> n >> k;
   
    for(int i = 0; i<n; i++) cin >> arr[i];
    
    bubble_sort();
    
    if(cnt < k) cout << -1;
}
