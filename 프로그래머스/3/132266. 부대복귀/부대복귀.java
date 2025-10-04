import java.util.*;
class Solution {
    static int[] visited;
    static List<Integer>[] G;
    static int N;   //  정점 갯수
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        N = n;
        // 그래프 생성 및 초기화
        G = new ArrayList[n+1];
        for (int i = 0; i < n+1; i++) {
            G[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < roads.length; i++) {
            int u = roads[i][0];
            int v = roads[i][1];
            G[u].add(v);
            G[v].add(u);
        } 
        
        
        int[] answer = new int[sources.length];
        bfs(destination);
        int index = 0;
        for (int i = 0; i < sources.length; i++) {
            answer[index] = visited[sources[i]] -1;
            index++;
        }
        return answer;
        
    }
    static void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited = new int[N+1];
        visited[s] = 1;
        while(!q.isEmpty()) {
            int v = q.poll();
            
            for (int u: G[v]) {
                if (visited[u] != 0) {
                    continue;
                }
                visited[u] = visited[v] +1;
                q.add(u);
            }
            
        }
    }
    
}