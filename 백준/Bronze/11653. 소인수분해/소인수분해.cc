#include <iostream>

using namespace std;

int main()
{
    int n;
    cin >> n;
   
    string res = "";
    
    int cp = n;
    for(int i = 2; i * i <= n; i++){
        while(cp % i == 0){
            cp /= i;
            res += to_string(i) + "\n";
        }
    }
    if(cp != 1) res += to_string(cp);
    
    cout << res;
    
    return 0;
}
