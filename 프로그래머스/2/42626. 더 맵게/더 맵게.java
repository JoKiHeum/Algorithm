import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int cnt = 0;
        for (int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        while (true) {
            int first = pq.poll();
            if(first >= K) {
                break;
            }
            else {
                if (pq.size() == 0){
                    return -1;
                }
                int second = pq.poll();
                int new_food = first + second * 2;
                cnt++;
                pq.add(new_food);
            }
        }
        return cnt;
    }
}