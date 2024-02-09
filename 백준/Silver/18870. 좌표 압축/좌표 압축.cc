#include <iostream>
#include <algorithm>
using namespace std;

// 1e6 

int n;
int arr[1000020];
int o[1000020];

int get_lower_bound(int target){
    int s = 0;
    int e = n;
    
    while(s <= e){
        int mid = (s+e) / 2;
        
        if(arr[mid] < target){
            s = mid + 1;
        }else {
            e = mid - 1;
        }
    }
    
    return s;
}

int main()
{
    cin >> n;
    
    for(int i = 0; i < n; i++){
        cin >> o[i];
        arr[i] = o[i];
    }
    
    
    sort(arr, arr+n);
    
    for(int i = 0; i < n; i++){
        if(arr[i] == arr[i+1]) arr[i] = 2e9;
    }
    
    sort(arr, arr+n);
    
    for(int i = 0; i < n; i++){
        cout << get_lower_bound(o[i]) << " ";
    }

    return 0;
}
