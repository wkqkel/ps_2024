#include <bits/stdc++.h>
using namespace std;

vector<string> alphas = {"c=","c-","dz=","d-","lj","nj","s=","z="};

void replaceAll(string& s, string w, string c){
  int pos = s.find(w);
  while(pos != string::npos) {
    s.replace(pos, w.length(), c);
    pos = s.find(w, pos + c.length());
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
    
  string s;

  getline(cin, s);
  
  for(string alpha : alphas){
    replaceAll(s, alpha, "#");
  }
  
  cout << s.length();
}

