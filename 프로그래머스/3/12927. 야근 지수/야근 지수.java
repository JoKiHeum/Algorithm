import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        // 내림차순 우선순위 큐 
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }
        
        while(!pq.isEmpty() && n > 0) {
            int work = pq.poll();
            work--;            
            n--;
            // 만약 잔업이 0이 되면
            if (work <= 0) {
                continue;
            }
            pq.add(work);
        }
        
        if (pq.isEmpty()) return 0;
        else {
            while(!pq.isEmpty()) {
                int work = pq.poll();
                answer += work * work;
            }
        }
        return answer;
        
        
        
    }
}