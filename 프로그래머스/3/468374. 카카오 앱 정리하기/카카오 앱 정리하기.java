import java.util.*;

class Solution {
    static int N; 
    static int M; 
    // 방향 - 1: 우, 2: 하, 3: 좌, 4: 상
    static int[] dr = {0, 0, 1, 0, -1};
    static int[] dc = {0, 1, 0, -1, 0};
    // 앱 별 사이즈 
    static int[] sizes;
    // 앱 좌표
    static List<List<int[]>> positions;
    // 정답
    static int[][] grid;
    
    public int[][] solution(int[][] board, int[][] commands) {
        // 초기화 세팅
        N = board.length;
        M = board[0].length;
        grid = new int[N][M];
        sizes = new int[101];  // 앱 최대 갯수 만큼 그냥 만들기
        positions = new ArrayList<>();
        
        for (int i = 0; i < 101; i++) {
            positions.add(new ArrayList<>());
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = board[i][j];
                grid[i][j] = num;
                // 초기 앱 별 좌표 저장
                if (num != 0) {
                    positions.get(num).add(new int[]{i,j});
                }
            }
        }
        
        for (int i = 1; i < 101; i++) {
            sizes[i] = (int) Math.sqrt(positions.get(i).size());
        }
        
        for (int[] cmd : commands) {
            command(cmd[0], cmd[1]);
        }
        return grid;
    }
    
    static void command(int app, int dir) {
        Set<Integer> seed = new HashSet<>();
        seed.add(app);
        
        while (!seed.isEmpty()) {
            Set<Integer> group = findMoveApp(seed, dir);
            moveApp(group,dir);
            seed = findSplitApp(group);
        }
    }
    
    // 같이 이동할 앱 찾기
    static Set<Integer> findMoveApp (Set<Integer> seed, int dir) {
        Set<Integer> group = new HashSet<>(seed);
        Queue<Integer> q = new LinkedList<>(seed);
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int[] position : positions.get(cur)) {
                int nr = (position[0] + dr[dir] + N) % N;
                int nc = (position[1] + dc[dir] + M) % M;
                
                int next = grid[nr][nc];
                
                if (next != 0 && !group.contains(next)) {
                    group.add(next);
                    q.add(next);
                }
            }
        }
        return group;
    }
    
    // 앱 이동
    static void moveApp (Set<Integer> group, int dir) {
        // 기존 앱 삭제
        for (int app : group) {
            for (int[] position : positions.get(app)) {
                grid[position[0]][position[1]] = 0;
            }
        }
        
        // 이동
        for (int app : group) {
            for (int[] position : positions.get(app)) {
                position[0] = (position[0] + dr[dir] + N) % N;
                position[1] = (position[1] + dc[dir] + M) % M;
                
                grid[position[0]][position[1]] = app;
            }
        }
    }
    
    // 짤린 앱이 있는지 확인
    static Set<Integer> findSplitApp (Set<Integer> group) {
        Set<Integer> splitApp = new HashSet<>();
        
        for (int app : group) {
            boolean isSplit = false;
            
            int maxR = -1;
            int maxC = -1;
            int minR = N;
            int minC = M;
            
            for (int[] position : positions.get(app)) {
                maxR = Math.max(maxR, position[0]);
                maxC = Math.max(maxC, position[1]);
                minR = Math.min(minR, position[0]);
                minC = Math.min(minC, position[1]);
            }
            
            int diffR = maxR - minR + 1;
            int diffC = maxC - minC + 1;
            
            if (diffR != sizes[app] || diffC != sizes[app]) {
                isSplit = true;
            }
            
            if (isSplit) {
                splitApp.add(app);
            }
        }
        return splitApp;
    }
}