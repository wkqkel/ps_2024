#include <map>

using namespace std;

int cnt = 1;
string res = "1";
map<char,int> trans = {
    {'A',1},
    {'E',2},
    {'I',3},
    {'O',4},
    {'U',5}
};

int solution(string words) {
    string nw = "";
    for(int i=0; i < words.size();i++) nw += to_string(trans[words[i]]);
    
    while(res != nw){
        cnt++;
        if(res.size() < 5){
            res += '1';
        } else { 
            res = to_string(stoi(res) + 1);
            while(res.back() == '6'){
                res.pop_back();
                res = to_string(stoi(res) + 1);
            }
        }
    }
    
    return cnt;
}