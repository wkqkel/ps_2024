class Solution {
    public int solution(int[] arr) {
        int ret = 0;
        int n = arr.length;
        for(int h = 1; h <= 10000; h++){
            int cnt = 0;
            for(int i = 0; i < n; i++){
                if(arr[i] >= h) cnt++;
            }
            if(cnt >= h) ret = h;
        }
        return ret;
    }
}