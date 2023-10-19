import java.util.*;

class Solution {
    static char[][] map;
    static int N, M, answer;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int startRow, startCol;
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        map = new char[N][M];
        int buttonRow = 0;
        int buttonCol = 0;
        int endRow = 0;
        int endCol = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = maps[i].charAt(j);
                map[i][j] = c;
                if (map[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                } else if (map[i][j] == 'L') {
                    buttonRow = i;
                    buttonCol = j;
                } else if (map[i][j] == 'E') {
                    endRow = i;
                    endCol = j;
                }
            }
        }
        
        // 출발점에서 레버로 가는 경로
        // 도착지 지나가기 가능
        // 레버까지 가지 못하면 바로 -1 리턴
        if (!bfs(buttonRow, buttonCol, true)) {
            return -1;
        }
        
        // 레버에서 도착지로 가는 경로
        // 출발지 레버 위치로 갱신
        startRow = buttonRow;
        startCol = buttonCol;
        if (!bfs(endRow, endCol, false)) {
            return -1;
        }
        return answer;
    }
    
    private static boolean bfs(int endRow, int endCol, boolean isFirst) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        queue.add(new int[] { startRow, startCol, 0});
        visited[startRow][startCol] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            if (cur[0] == endRow && cur[1] == endCol) {
                answer += cur[2];
                return true;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == 'X') continue;
                
                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc, cur[2] + 1});
            }
        }
        return false;
    }
}