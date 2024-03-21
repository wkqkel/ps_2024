#include <string>
#include <vector>
#include <map>
#include <iostream>
using namespace std;

const int MX = 1e6+2;
int arr1[MX];
int sz1 = 0;

int arr2[MX];
int sz2 = 0;

int solution(vector<int> arr) {
    int n = arr.size();
    int cnt = 0;
    
    for(int i = 0; i < n; i++){
        int cur = arr[i];
        if(arr2[cur] == 0) sz2++;
        arr2[cur]++;
    }
    
    int res = 0;
    
    for(int i = 0; i < n; i++){
        int cur = arr[i];
        if(arr1[cur] ==0) sz1++;
        arr1[cur]++;
        arr2[cur]--;
        if(arr2[cur] ==0) sz2--;
        if(sz1 == sz2) res++;
    }

    return res;
}