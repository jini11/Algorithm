import java.util.*;

class Solution {
    
    static int n, m;
    static int[][] map;
    static boolean[] selected;
    static Map<Integer, Integer> oil;
    
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public int solution(int[][] land) {
        int answer = 0;
        
        map = copyMap(land);
        n = land.length;
        m = land[0].length;
        
        int area = 2;
        oil = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    int size = bfs(i, j, area);
                    oil.put(area, size);
                    area++;
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            selected = new boolean[oil.size()];
            int amount = getOil(i);
            answer = Math.max(answer, amount);
        }
        
        return answer;
    }
    
    private static int getOil(int col) {
        int sum = 0;
        
        for (int i = 0; i < n; i++) {
            if (map[i][col] != 0) {
                selected[map[i][col] - 2] = true;
            }
        }
        
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                sum += oil.get(i + 2);
            }
        }
        
        return sum;
    }
    
    private static int bfs(int row, int col, int area) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { row, col });
        int size = 1;
        map[row][col] = area;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (map[nr][nc] == 1) {
                    queue.add(new int[] { nr, nc });
                    map[nr][nc] = area;
                    size++;
                }
            }
        }
        
        return size;
    }
    
    private static int[][] copyMap(int[][] map) {
        int[][] copyMap = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }
}