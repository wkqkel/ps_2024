#include <string>
#include <vector>
#include <set>
#include <iostream>
#include <map>

using namespace std;

int n;
vector<int> vec;
bool ch[10];
int arr[10];

set<int> sets;

bool is_prime(int num){
    if(num <= 1) return false;
    
    for(int i = 2; i * i <= num; i++){
        if(num % i == 0) return false;
    }
    return true;
}

void recur(int cur){
    if(cur > 0){
        string str = "";
        for(int i = 0; i < cur; i++) {
            str += to_string(arr[i]);
        }
        int nm = stoi(str);

        if(is_prime(nm)) {
            // cout << nm << "\n";
            sets.insert(nm);  
        }
    }

    if(cur == n){
        return;
    }

    for(int i = 0; i < vec.size(); i++){
        if(ch[i]) continue;
        ch[i] = 1;
        arr[cur] = vec[i];
        recur(cur+1);
        ch[i] = 0;
    }
}

int solution(string numbers) {
  for(int i = 0; i < numbers.size(); i++){
      vec.push_back(numbers[i] - '0');
  }
  n = vec.size();
  recur(0);
  return sets.size();
}