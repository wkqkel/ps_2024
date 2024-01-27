#include <iostream>
#include <algorithm>

using namespace std;

int n,q;

string arr[1000];
int cnt = 0;
bool is_sum_zero(string res){
    int sum = 0;
    string tmp = "";
    char op = '+';

    for(int i = 0; i < res.size();i++){
       
        if('0'<=res[i] && res[i] <= '9') {
            tmp += to_string(res[i] - '0');
        } 
        if(res[i] == '+' || res[i] == '-' || i == res.size()-1){
            if(op == '+') sum += stoi(tmp);
            if(op == '-') sum -= stoi(tmp);
            tmp = "";
            op = res[i];
        }
    }
    
    return sum == 0;
}

void recur(int cur, string res){
    if(cur == n) {
        if(is_sum_zero(res)) arr[cnt++] = res;
        return;
    }
 
    recur(cur+1, res + "+" + to_string(cur+1));
    recur(cur+1, res + "-" + to_string(cur+1));
    recur(cur+1, res + " " + to_string(cur+1));
}

int main()
{
    
    cin >> q;
    while(q--){
        cin >> n;
        cnt = 0;
        
        recur(1, "1");
   
        sort(arr, arr+cnt);
        for(int i = 0; i < cnt; i++) cout << arr[i] << '\n';
        cout << '\n'; 
    }
    
    return 0;
}
