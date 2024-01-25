#include <iostream>

using namespace std;

bool is_palid(string str, int& l, int& r){
   while(l<r){
       if(str[l] != str[r]) return false;
       l++;
       r--;
   }
   return true;
}

bool is_similar_palid(string str, int l, int r){
    int l1 = l+1, r1 = r-1;
    return is_palid(str, l1,r) || is_palid(str, l, r1);
}

int main()
{
    int n;
    cin >> n;

    while(n--){
        string v;
        cin >> v;
        int res = 0;
        int l = 0, r = v.size() - 1;
        if(is_palid(v,l,r)){
            res = 0;
        } else if(is_similar_palid(v, l, r)){
            res = 1;
        } else {
            res = 2;
        }
        cout << res << '\n';
    }
}