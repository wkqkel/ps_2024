#include <iostream>
#include <algorithm>

using namespace std;



int main()
{
    
  bool is_prime[10002];
  fill(is_prime, is_prime + 10002, true);
  is_prime[1] = false;
  for(int i = 2; i *i <=10002; i++){
      if(!is_prime[i]) continue;
      for(int j = i*i; j <=10002; j+=i){
          is_prime[j] = false;
      }
  }
  int m,n;
  cin >> m >> n;
  int sum = 0;
  int mn = 0;
  for(int i = m; i <=n ;i++){
      if(!is_prime[i]) continue;
      sum += i;
      if(mn) continue;
      mn = i;
  }
  if(sum == 0) cout << -1;
  else cout << sum << '\n' << mn;
}
