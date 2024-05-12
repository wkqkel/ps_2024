#include <iostream>
#include <cmath>
using namespace std;

int cnt1[26];
int cnt2[26];

int main()
{
    string a, b;
    cin >> a >> b;
    for(char c: a){
        cnt1[c - 'a']++;
    }
    for(char c: b){
        cnt2[c - 'a']++;
    }
    
    int sum = 0;
    
    for(int i = 0; i < 26; i++){
        sum += abs(cnt1[i] - cnt2[i]);
    }
    
    cout << sum;

    return 0;
}
