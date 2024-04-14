function solution(arr, k) {
    const n = arr.length;
    let i = 0;
    let j = 0;
    let sum = arr[i];
    let ans = [0,1e9];
    
    while(j < n){
        if(sum > k) {
            sum -= arr[i++];
        } else {  
            if(sum == k) {
                if(ans[1] - ans[0] > j - i) {
                    ans[0] = i;
                    ans[1] = j;
                }
            }
           
            sum += arr[++j];
        }
    }
    
    return ans;
}