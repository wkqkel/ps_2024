#include <string>
#include <vector>
#include <iostream>

using namespace std;

string op = "+-*";
char arr[3];
bool ch[3];
long mx = -1;
string exp;

vector<long> nums;
vector<char> ops;

void sep(string exp){
    string num = "";
    exp+= "?";
    for(int i = 0; i < exp.size();i++){
        char cur = exp[i];
       if(isdigit(cur)){
           num += cur;
       } else {
           if(i!=exp.size()-1)ops.push_back(cur);
           nums.push_back(stoi(num));
           num = "";
       }
    }
}

long cal(){
    nums ={};
    ops ={};
    sep(exp);
    
    long res;
    for(int i = 0; i < 3; i++){
        for(int j = 0; j < ops.size(); j++){
            if(arr[i]!=ops[j]) continue;
            long newN;
            if(arr[i] == '+'){
                newN = nums[j]+nums[j+1];
            }
            if(arr[i] == '*'){
                newN = nums[j]*nums[j+1];
            }
            if(arr[i] == '-'){
                newN = nums[j]-nums[j+1];
            }
          
            ops.erase(ops.begin()+j);
            nums.erase(nums.begin()+j);
            nums[j]= newN;
            j--;
        }
    }
 
    return abs(nums[0]);
}

void recur(int cur){
    if(cur == 3){
        mx = max(mx, cal());
        return;
    }
    for(int i = 0; i<3; i++){
        if(ch[i]) continue;
        arr[cur] = op[i];
        ch[i] = 1;
        recur(cur+1);
        ch[i] = 0;
    }
}




long long solution(string expr) {
    exp = expr;
    long long answer = 0;
 
    recur(0);

    return mx;
}







