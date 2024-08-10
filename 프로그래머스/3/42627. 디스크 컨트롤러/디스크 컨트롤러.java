import java.util.*;

/**
고려못한 점
1. 한번 작업을 시작하면 그 작업은 계속. 매시간마다 재정렬 X
2. 원소가 들어오게되면, pq다시 정렬하기에 작업중이던게 바뀔 수 있음.
3. 시간에 들어올 수 있는게 여러개가 될 수 있음. 하지만 처리는 하나씩.
*/
class Task implements Comparable<Task> {
    public int in;
    public int remain;
    public boolean isWorking = false;
    
    public Task(int in, int remain){
        this.in = in;
        this.remain = remain;
    }
    
    public void work(){
        this.isWorking = true;
        this.remain--;
    }
    
    public int compareTo(Task o){
        if(this.isWorking){
            return 0;
        }
        if(o.isWorking){
            return 1;
        }
        
        return this.remain - o.remain;
    }
}

class Disk {
    int t = 0;
    int ret = 0;
    int n = 0;
    
    List<Task> waits = new LinkedList<>();
    PriorityQueue<Task> pq = new PriorityQueue<>();
    
    public Disk(int[][] jobs){
        n = jobs.length;
        for(int[] job: jobs){
            waits.add(new Task(job[0], job[1]));
        }
        Collections.sort(waits, (o1, o2)->o1.in - o2.in);
    }
    public void run(){
        while(true){
            while(waits.size() > 0 && waits.get(0).in == t) {
                pq.add(waits.get(0));
                waits.remove(0);
            }
            t++; 
            if(pq.isEmpty()){
                if(waits.size() == 0) break;
            } else {
                Task task = pq.peek();
                task.work();
                if(task.remain <= 0) {
                    ret += t - task.in;
                    pq.poll();
                }
            }
        }
    }
    
    public int getResult(){
        return ret / n;
    }
}

class Solution {
    
    public int solution(int[][] jobs) {     
        Disk disk = new Disk(jobs);
        disk.run();
        
        return disk.getResult();
    }
}