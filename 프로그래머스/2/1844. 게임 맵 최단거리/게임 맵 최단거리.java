import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        
        int n = maps.length;
        int m = maps[0].length;
        
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new ArrayDeque<>();
        visited[0][0] = true;
        queue.add(new int[] {0, 0, 0});
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == n-1 && cur[1] == m-1) {
                return cur[2] + 1;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= m || maps[nr][nc] == 0 || visited[nr][nc]) continue;
                
                queue.add(new int[] {nr, nc, cur[2] + 1});
                visited[nr][nc] = true;
            }
        }
        return -1;
    }
}