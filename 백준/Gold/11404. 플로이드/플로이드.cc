#include <iostream>

using namespace std;

const int MX = 102;

int dist[MX][MX];

int n, m;





int main()
{
    cin >> n >> m;
    
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            dist[i][j] = 1e9;
        }
    }


    for(int t = 0; t < m; t++){
        int a, b, c;
        cin >> a >> b >> c;
        
        dist[a][b] = min(c, dist[a][b]);
    }
    
    for(int i = 1; i <= n; i++) dist[i][i] = 0;
    
   for(int k = 1; k <= n; k++){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
    
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            if(dist[i][j] == 1e9) cout << 0 << " ";
            else cout << dist[i][j] << " ";
        }
        cout << "\n";
    }

    return 0;
}
