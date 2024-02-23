#include <iostream>

using namespace std;

int k;

bool ch[10];
int arr[10];
char str[10];
int flag = true;

bool check(){
    for(int i = 0; i < k; i++){
        if(str[i] == '<' && arr[i] >= arr[i+1]) {
            return false;
        } 
        if(str[i] == '>' && arr[i] <= arr[i+1]) {
            return false;
        } 
    }
    return true;
}

void getLast(int cur){
    if(cur == k+1){
        if(!flag ||!check()) return;
        for(int i = 0; i < k+1; i++) cout << arr[i];
        cout << '\n';
        flag = false;
        return;
    }
    for(int i = 9; i >= 0; i--){
        if(ch[i]) continue;
        ch[i] = true;
        arr[cur] = i;
        getLast(cur+1);
        ch[i] = false;
    }
}

void getFirst(int cur){
    if(cur == k+1){
        if(!flag ||!check()) return;
        for(int i = 0; i < k+1; i++) cout << arr[i];
        cout << '\n';
        flag = false;
        return;
    }
    for(int i = 0; i <= 9; i++){
        if(ch[i]) continue;
        ch[i] = true;
        arr[cur] = i;
        getFirst(cur+1);
        ch[i] = false;
    }
}


int main()
{
    cin >> k;
    
    for(int i = 0; i < k; i++) cin >> str[i];
    
    getLast(0);
    flag = true;
    getFirst(0);
}
