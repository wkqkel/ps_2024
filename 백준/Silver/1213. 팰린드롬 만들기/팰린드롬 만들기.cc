#include <iostream>
#include <algorithm>
using namespace std;

const int MX = 54;

string str;
int arr[26];

string repeat(char c, int n){
    string ret = "";
    for(int i = 0; i < n; i++) ret += c;
    return ret;
}
int main()
{
    cin >> str;
    for(char c: str){
        arr[c - 'A']++;
    }

    // 홀수가 2개이상이면 fail
    int cnt_h = 0;
    for(int i : arr){
        if(i % 2 == 1) cnt_h++;
    }
    if(cnt_h >= 2){
        cout << "I'm Sorry Hansoo";
        exit(0);
    }

    string left = "";
    string center = "";
    string right;
    for(int i = 0; i < 26; i++){
        int cnt = arr[i];
        char c = 'A' + i;
        if(cnt == 0) continue;
        left += repeat(c, cnt / 2); 
        // 만약 홀수가 있다면, 센터에 더해주기
        if(cnt % 2 == 1) center += c;
    }
    
    right = left;
    reverse(right.begin(), right.end());
    cout << left + center + right;

    return 0;
}