import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int hIndex = 0;
        int N = citations.length;   //   논문의 수
        
        Arrays.sort(citations);
        
        
        for (int i = 0; i < N; i++) {
            int h = N - i;
            if (citations[i] >= h) {
                hIndex = h;
                break;
            }
        }
        return hIndex;
    }
}