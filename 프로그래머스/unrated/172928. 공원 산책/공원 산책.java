class Solution {
    static int R, C;
    static int curRow, curCol;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        
        R = park.length;
        C = park[0].length();
        
        map = new char[R][C];
        curRow = 0;
        curCol = 0;
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = park[i].charAt(j);
                if (map[i][j] == 'S') {
                    curRow = i;
                    curCol = j;
                    map[i][j] = 'O';
                }
            }
        }
        
        for (int i = 0; i < routes.length; i++) {
            String[] command = routes[i].split(" ");
            dfs(curRow, curCol, getDir(command[0]), Integer.parseInt(command[1]));
        }
        
        answer[0] = curRow;
        answer[1] = curCol;
        
        return answer;
    }
    
    private static void dfs(int row, int col, int dir, int len) {
        if (len == 0) {
            curRow = row;
            curCol = col;
            return;
        }
        int nr = row + dr[dir];
        int nc = col + dc[dir];
        
        if (nr < 0 || nr >= R || nc < 0 || nc >= C) return;
        if (map[nr][nc] == 'X') return;
        
        dfs(nr, nc, dir, len - 1);
    }
    
    private static int getDir(String dir) {
        String[] d = {"N", "E", "S", "W"};
        for (int i = 0; i < d.length; i++) {
            if (d[i].equals(dir)) {
                return i;
            }
        }
        return -1;
    }
}