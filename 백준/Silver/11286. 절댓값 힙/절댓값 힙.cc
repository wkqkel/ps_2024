#include <iostream>
#include <queue>

using namespace std;

class cmp {
    public:
        bool operator() (int o1, int o2){
            if(abs(o1) == abs(o2)){
                return o1 - o2 > 0;
            }
            
            return abs(o1) - abs(o2) > 0;
        }
};


int main()
{
    priority_queue<int, vector<int>, cmp> pq;
    
    int n;
    cin >> n;
    
    string ret ="";
    for(int i = 0; i < n; i++){
        int v;
        cin >> v;
        if(v == 0) {
            if(pq.empty()) ret += to_string(0);
            else {
                ret += to_string(pq.top());
                pq.pop();
            }
            ret += "\n";
        }
        else {
            pq.push(v);
        }
    }
    
    cout << ret;

    return 0;
}