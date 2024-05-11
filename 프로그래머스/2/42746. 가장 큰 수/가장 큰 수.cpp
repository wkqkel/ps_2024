#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

bool compare(int a, int b){
    string a_str = to_string(a);
    string b_str = to_string(b);
    return stoi(b_str+a_str) < stoi(a_str+b_str);
}

string solution(vector<int> numbers) {
    string answer = "";
    sort(numbers.begin(),numbers.end(), compare);
    for(int i = 0; i <numbers.size(); i++){
        answer += to_string(numbers[i]);
    }
    if(answer[0] == '0'){
        return "0";   
    }
    return answer;
}