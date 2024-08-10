import java.util.*;

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
            // 1. 현재 시간에 맞는 것 다 넣음.
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
        int answer = 0;
        
        Disk disk = new Disk(jobs);
        disk.run();
        
        return disk.getResult();
    }
}