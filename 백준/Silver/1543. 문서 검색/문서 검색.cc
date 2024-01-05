#include <bits/stdc++.h>
using namespace std;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  string s, w;

  getline(cin, s);
  getline(cin, w);

  int cnt = 0;

  int pos = s.find(w, 0);
  while(pos != string::npos){
    cnt++;
    pos = s.find(w, pos + w.size());
  }
  
  cout << cnt;
}