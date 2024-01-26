#include <iostream>

using namespace std;

int main()
{

    while(true){
        int n;
        cin >> n;
        if(n == -1) break;
        
        string res = "";
        int sum = 0;
        for(int i = 1; i < n; i++){
           if(n % i != 0) continue;
           sum += i;
           if(i != 1) res += " + ";
           res += to_string(i);
        }
        
        if(sum == n) res = to_string(n) + " = " + res;
        else res =  to_string(n) + " is NOT perfect.";
        
        cout << res << '\n';
    }

    return 0;
}