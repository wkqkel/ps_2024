#include <iostream>
#include <queue>

using namespace std;

int main()
{
    queue<int> q;
    int n, k;
    cin >> n >> k;
    for(int i = 1; i <= n; i++) q.push(i);
    vector<int> res;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < k - 1; j++){
            q.push(q.front());
            q.pop();
        }
        res.push_back(q.front());
        q.pop();
    }
    cout << "<";
    for(int i = 0; i < n; i++){
        cout << res[i];
        if(i != n-1) cout <<", ";
    }
    cout << ">";
    

    return 0;
}
