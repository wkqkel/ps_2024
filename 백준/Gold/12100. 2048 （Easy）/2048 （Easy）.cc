#include <iostream>
#include <vector>

using namespace std;

/**
설계
1. 경우의 수 구하기
5번 이동시킨 경우의 수
4^5 = 2 ^ 10
2. 회전하기
방향에 따라 판자체를 회전한뒤
20 * 20 * 4
3. 이동 & 합치기
부딪힐 때까지 방향으로 가고,
같은 수 && 이미 합쳐진게 아니라면, 합치기
20  * 20 * 20 

4. 보드에서 가장 큰 블록 출력하기 
20 * 20

--- 
설계시 고려못한점 & 막힌 구간
1. 전체가 아닌 한번의 이동에서 합쳐진 것만 다시 못합침
+
2. 매테스트케이스마다 보드 초기화
*/

const int MX = 22;
int n;
int origin[MX][MX];
int board[MX][MX];
int tmp[MX][MX];
int arr[MX];

vector<string> cases;

void recur(int cur){
    if(cur == 5){
        string str = "";
        for(int i = 0; i < 5; i++){
            str += to_string(arr[i]);
        } 
        cases.push_back(str);
        return;
    }
    for(int i = 0; i < 4; i++){
        arr[cur] = i;
        recur(cur+1);
    }
}

void rotate(){
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n;j++){
            tmp[j][n-i-1] = board[i][j];
        }
    }
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            board[i][j] = tmp[i][j];
        }
    }
}

void move(auto& arr){
    bool ch[n];
    fill(ch, ch+n, false);
    
    for(int i = 1; i < n; i++){
        int cur = i;
        int pre = i - 1;
           
        while(true){
            if(pre < 0) break;
            if(arr[pre] == 0) {
                swap(arr[pre], arr[cur]);
                swap(ch[pre], ch[cur]);
            }
            else if(arr[pre] == arr[cur]){
                if(ch[pre] || ch[cur]) break;
                arr[pre] *= 2;
                arr[cur] = 0;
                ch[pre] = true;
                ch[cur] = false;
            }
            pre--;
            cur--;
        }
    }
}


int main()
{
    cin >> n;
    for(int i =0; i < n; i++){
        for(int j = 0; j < n; j++){
            cin >> origin[i][j];
        }
    }
    recur(0);
    
  
    int mx = -1;
    // 1. 경우의 수 구하기
    for(string t : cases){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = origin[i][j];
            }
        }
        
        for(char c_dir : t){
            int dir = (int)(c_dir -'0');
           
            for(int i = 0; i < dir; i++){
                rotate();
            }
            for(int i = 0; i < n; i++){
                 move(board[i]);
            }
            for(int i = 0; i < 4 - dir; i++){
                rotate();
            }
        }
        
        // 3. 보드에서 가장 큰 블록 찾기
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                mx = max(board[i][j], mx);
            }
        }
    }
    
    cout << mx;

    return 0;
}