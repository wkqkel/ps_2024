/**
- n개의 퍼즐을 시간내에 풀어여함
- 각 퍼즐은 난이도 와 소요시간 존재
- 내 숙련도에 따라 틀리는 횟수가 바뀜.
- 현재 난이도 curLv, 현재퍼즐 소요시간 tcur, 이전 tprv, 숙련도 myLv
게임진행
- curLv <= myLv면 
    - 퍼즐을 틀리지않고 tcur 시간 사용 후 해결
- curLv > myLv 면 
    - curLv - myLv번 틀림 
    - n번 틀린다면 n * (tcur + tprv) 사용 후 tcur 사용해서 해결
- 만족하는 lv의 최솟값 구하기
- 푸는데 걸린 시간이 limt보다 작으면 만족.
- 레벨이 높을수록 최소 시간이 걸림

### 접근
- 1. curLv돌면서 (~1e5)
- 2. diff돌면서, 푸는데 걸린 시간이 
    - limit보다 작으면, 답이 될 수 있고, 오른쪽엔 답이 없음.
- getSolveTime() 푸는데 걸리는 시간 구하기
    - diffs돌면서
    - curLv <= myLv면 
        - ret += tcur
    - curLv > myLv = n 면 
    -   ret += n * (tcur + tprv) + tcur;
*/
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int s = 0;
        int e = (int) 1e5 + 1;
        
        while(s + 1 < e){
            int mid = (s + e) / 2;
            if(getSolveTime(diffs,times, mid) <= limit){
                e = mid;
            } else {
                s = mid;
            }
        }
        
        return s + 1;
    }
    
    static long getSolveTime(int[] diffs,int[] times, int myLv){
        long ret = times[0];
        for(int i = 1; i < diffs.length; i++){
            int tprv = times[i-1];
            int tcur = times[i];
            int curLv = diffs[i];
            if(curLv < myLv){
                ret += tcur;
            } else {
                int n = curLv - myLv;
                ret += (long) n * (tcur + tprv) + tcur;
            }
        }
        return ret;
    }
}