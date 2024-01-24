#include <iostream>

using namespace std;

int n, k;
int A[500020];
int tmp[500020];
int cnt = 0;
int res = -1;

void merge(int p, int q, int r){
    int i = p;
    int j = q + 1;
    int t = 1;
    while(i <= q && j <= r){
        if(A[i] <= A[j]) tmp[t++] = A[i++];
        else tmp[t++] = A[j++];
    }
    while(i <= q) tmp[t++] = A[i++];
    while(j <= r) tmp[t++] = A[j++];
    
    i = p; t = 1;
    while(i <= r) {
        if(++cnt == k) res = tmp[t];
        A[i++] = tmp[t++];
    }
}

void merge_sort(int p, int r){
    if(p < r){
        int q = (p + r) / 2;
        merge_sort(p, q);
        merge_sort(q+1, r);
        merge(p, q, r);
    }
}

int main()
{
    cin >> n >> k;
    for(int i = 0; i<n;i++) cin >> A[i];
    merge_sort(0, n-1);
    cout << res;
    return 0;
}
