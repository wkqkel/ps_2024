/**
# 문제해석
- 동영상 재생기 
- 기능 3가지
- 1. 10초전 이동
    - 현재 위치가 10초 미만일 경우, 처음 이동(0분 0초)
- 2. 10초후 이동
    - 남은 시간이 10초 미만일 경우, 마지막 위치(동영상의 길이)
- 3. 오프닝 건너띄기
    - 오프닝 구간 이내일 경우 오프닝이 끝나는 위치로 이동
    
# 입력
mm:ss 형식으로 모두 주어짐 
*/



class Solution {

    public String solution(String len, String pos, String os, String oe, String[] commands) {
        int cur = to(pos);
        int end = to(len);
        int ops = to(os);
        int ope = to(oe);
         System.out.println(cur);
        for(String cmd: commands){
                       
            if(ops <= cur && cur <= ope){
                cur = ope;
            }
            if("prev".equals(cmd)){
                cur = Math.max(cur - 10, 0);
            }
            else if("next".equals(cmd)){
                cur = Math.min(cur + 10, end);
            }
            if(ops <= cur && cur <= ope){
                cur = ope;
            }
        }
        return to(cur);
    }
    
    static int to(String st){
        int h = Integer.parseInt(st.split(":")[0]) * 60;
        int m = Integer.parseInt(st.split(":")[1]);
        return  h + m;
    }
    static String to(int i){
        return String.format("%02d", i / 60) + ":" + String.format("%02d", i % 60);
    }
    
    
    
    
}