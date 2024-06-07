#include <iostream>
#include <deque>

using namespace std;

const int MX = 100002;
int arr[MX];

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    
    int T, n;
    cin >> T;
    
  

    for(int t = 0; t < T; t++){
        string cmd;
        cin >> cmd >> n;

        deque<int> dq;
        string num;
        char input;
        
        while(true){
            cin >> input;
            if(input == '[') continue;
            else if(input == ',') {
                if(num != "") dq.push_back(stoi(num));
                num = "";
            }
            else if(input == ']'){
                if(num != "") dq.push_back(stoi(num));
                break;
            }
            else {
                num += input;
            }
        }
    
        bool isError = false;
        bool isReverse = false;
  
        for(char c : cmd){
            if(c == 'R'){
                isReverse = !isReverse;
            } else {
                if(dq.empty()) {
                    isError = true;
                    break;
                }
                if(isReverse) dq.pop_back();
                else dq.pop_front();
            }
        }
        
        if(isError){
            cout << "error\n";
            continue;
        }
        
        cout << '[';
        for(int i = 0; i < dq.size(); i++){ 
            if(isReverse) cout << *(dq.end() - i - 1);
            else cout << *(dq.begin() + i);
            if(i != dq.size() - 1) cout << ",";
        }
        cout << ']' << '\n';
    }

    
    return 0;
}
