#include <iostream>

using namespace std;

struct pq {
    int heap[100005];
    int sz = 0;
    
    void push(int x){
        heap[++sz] = x;
        int cur = sz;
        while(true){
            int par = cur / 2;
            if(heap[par] < heap[cur] || par < 1) break;
            swap(heap[par], heap[cur]);
            cur /= 2;
        }
    }
    
    int top(){
        return heap[1];
    }
    
    int pop(){
        int ret = heap[1];
        swap(heap[1], heap[sz--]);
        int cur = 1;
        while(true){
            int lt = cur * 2;
            int rt = cur * 2 + 1;
            int nxt = cur;
            
            if(lt <= sz && heap[lt] < heap[nxt]) nxt = lt;
            if(rt <= sz && heap[rt] < heap[nxt]) nxt = rt;
            
            if(nxt == cur) break;
            
            swap(heap[cur], heap[nxt]);
            cur = nxt;
        }
        
        return ret;
    }
    
};

int main()
{
    int n;
    pq pq;
    
    cin >> n;
    string ret = "";
    for(int i = 0; i < n; i++){
        int x;
        cin >> x;
        if(x == 0) {
            if(pq.sz == 0) ret += to_string(0);
            else {
                ret += to_string(pq.pop());
            }
            ret += "\n";
        } else {
            pq.push(x);
        }
        
    }
    cout << ret;
    return 0;
}