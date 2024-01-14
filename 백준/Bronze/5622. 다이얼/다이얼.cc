#include <iostream>
#include <map>

using namespace std;

map<char, int> c_map;


int main(){
   string s;
   cin >> s;
 
  int res = 0;
  
  for(int i = 'A'; i <= 'Z'; i++){
    char c = char(i);
    int t = 2;
    if(c >= 'A') t++;
    if(c >= 'D') t++;
    if(c >= 'G') t++;
    if(c >= 'J') t++;
    if(c >= 'M') t++;
    if(c >= 'P') t++;
    if(c >= 'T') t++;
    if(c >= 'W') t++;
    c_map[c] = t;
  }
  
  for(char c:s){
    res += c_map[c];
  }
  
  cout << res;
}
