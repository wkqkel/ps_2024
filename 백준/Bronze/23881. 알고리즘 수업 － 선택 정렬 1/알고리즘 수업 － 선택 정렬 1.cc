#include <iostream>

using namespace std;

int n, k;
int cnt = 0;
string res = "-1";
int arr[10020];

void selection_sort(){
    for(int i = n-1; i >= 0;i--){
        int mx = -1;
        int idx = -1;
        for(int j = i; j >=0; j--){
           if(mx < arr[j]){
               idx = j;
               mx = arr[j];
           }
        }
        if(mx != arr[i]) {
            swap(arr[idx], arr[i]);
            if(++cnt == k) res = to_string(arr[idx]) + " " + to_string(arr[i]);
        }
    }
}

int main()
{   

   cin >> n >> k;
  
   for(int i = 0; i < n; i++) cin >> arr[i];
   selection_sort();
   cout << res;
   
}
