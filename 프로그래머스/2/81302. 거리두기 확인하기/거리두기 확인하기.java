import java.util.*;
class Solution {
    private static final int SIZE = 5;
    private static int[] dr = {0, 1, 0, -1};
    private static int[] dc = {1, 0, -1, 0};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for (int t = 0; t < places.length; t++) {
            char[][] map = new char[SIZE][SIZE];
            List<int[]> person = new ArrayList<>();
            for (int i = 0; i < SIZE; i++) {
                String line = places[t][i];
                for (int j = 0; j < SIZE; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == 'P') {
                        person.add(new int[] {i, j});
                    }
                }
            }
            
            boolean isRight = true;
            for (int i = 0; i < person.size(); i++) {
                // bfs(map, person.get(i));
                if (!bfs(map, person.get(i))) {
                    // System.out.println(i);
                    isRight = false;
                    break;
                }
            }
            
            if (isRight) {
                answer[t] = 1;
            } else {
                answer[t] = 0;
            }
        }
        
        return answer;
    }
    
    private static boolean bfs(char[][] map, int[] person) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        queue.add(new int[] { person[0], person[1], 0 });
        visited[person[0]][person[1]] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[2] > 1) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                
                if (nr < 0 || nr >= SIZE || nc < 0 || nc >= SIZE || visited[nr][nc]) continue;
                if (map[nr][nc] == 'X') continue;
                if (map[nr][nc] == 'P') return false;
                if (map[nr][nc] == 'O') {
                    queue.add(new int[] {nr, nc, cur[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }
        return true;
    }
    
    private static void printMap(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}