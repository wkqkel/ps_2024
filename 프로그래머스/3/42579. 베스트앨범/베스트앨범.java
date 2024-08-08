import java.util.*;

class Item implements Comparable<Item> {
    int i; int cnt; int g_cnt;
    String g;
    
    public Item(int i, int cnt, int g_cnt, String g){
        this.i = i;
        this.cnt = cnt;
        this.g_cnt = g_cnt;
        this.g = g;
    }
   
    public int compareTo(Item o){
        if(this.g_cnt == o.g_cnt){
            if(this.cnt == o.cnt) return this.i - i;
            return o.cnt - this.cnt;
        }
        return o.g_cnt - this.g_cnt;
    }
}
class Solution {
    
    public int[] solution(String[] genres, int[] plays) {
        // 장르 -> 많이 재생 -> 고유번호가 낮은.
        int n = genres.length;
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            if(map.get(genres[i]) != null){
                map.put(genres[i], map.get(genres[i]) + plays[i]);
            } else {
                map.put(genres[i], plays[i]);
            }
        }
        
        Item[] arr = new Item[n];
        for(int i = 0; i < n; i++){
            arr[i] = new Item(i, plays[i], map.get(genres[i]),genres[i]);
        }
        Arrays.sort(arr);
        Map<String, Integer> cnt = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
        
            if(cnt.get(arr[i].g) == null){
                cnt.put(arr[i].g, 1);
                list.add(arr[i].i);
            } else if(cnt.get(arr[i].g) < 2){
                cnt.put(arr[i].g, cnt.get(arr[i].g)+1);
                list.add(arr[i].i);
            }
           
            
        }
        int m = list.size();
        int ret[] = new int[m];
        for(int i = 0; i < m; i++){
            ret[i] = list.get(i);
        }
        
        return ret;
    }
}