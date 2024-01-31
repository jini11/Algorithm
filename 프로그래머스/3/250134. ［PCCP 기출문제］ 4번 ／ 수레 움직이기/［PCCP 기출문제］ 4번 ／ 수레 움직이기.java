class Solution {
    
    int N, M, res;
    boolean[][] visitedRed, visitedBlue;
    Point red, blue, redEnd, blueEnd;
    int[] dr = {1, 0, -1, 0};
    int[] dc = {0, 1, 0, -1};
    
    class Point {
        int row, col;
        
        Point (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        visitedRed = new boolean[N][M];
        visitedBlue = new boolean[N][M];
        res = Integer.MAX_VALUE;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == 1) {
                    red = new Point(i, j);
                    maze[i][j] = 0;
                } else if (maze[i][j] == 2) {
                    blue = new Point(i, j);
                    maze[i][j] = 0;
                } 
            }
        }
        
        dfs(red, blue, maze, 0);
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    
    private void dfs(Point red, Point blue, int[][] maze, int cnt) {
        if (res < cnt) {
            return;
        }
        
        if (isEnd(red, blue, maze)) {
            res = Math.min(res, cnt);
            return;
        }
        
        visitedRed[red.row][red.col] = true;
        visitedBlue[blue.row][blue.col] = true;
        
        Point nRed, nBlue;
        for (int i = 0; i < 4; i++) {
            int nrRed = red.row + dr[i];
            int ncRed = red.col + dc[i];
            
            // 도착했으면 유지
            if (maze[red.row][red.col] == 3) {
                nRed = new Point(red.row, red.col);
            } else if (!canGo(nrRed, ncRed, new Point(-1, -1), visitedRed, maze)) continue;
            else {
                nRed = new Point(nrRed, ncRed);
            }
            
            for (int j = 0; j < 4; j++) {
                int nrBlue = blue.row + dr[j];
                int ncBlue = blue.col + dc[j];
                
                if (maze[blue.row][blue.col] == 4) {
                    nBlue = new Point(blue.row, blue.col);
                } else if (!canGo(nrBlue, ncBlue, nRed, visitedBlue, maze)) continue;
                else {
                    nBlue = new Point(nrBlue, ncBlue);
                }
                
                if (nRed.row == nBlue.row && nRed.col == nBlue.col) continue;
                if (nRed.row == blue.row && nRed.col == blue.col && nBlue.row == red.row && nBlue.col == red.col) continue;
                dfs(nRed, nBlue, maze, cnt + 1);
            }
        }
        
        visitedRed[red.row][red.col] = false;
        visitedBlue[blue.row][blue.col] = false;
    }
    
    private boolean isEnd(Point red, Point blue, int[][] maze) {
        if (maze[red.row][red.col] == 3 && maze[blue.row][blue.col] == 4) {
            return true;
        }
        return false;
    }
   
    
    private boolean canGo(int row, int col, Point other, boolean[][] visited, int[][] maze) {
        if (row < 0 || row >= N || col < 0 || col >= M || visited[row][col] || maze[row][col] == 5) {
            return false;
        } 
        if (row == other.row && col == other.col) {
            return false;
        }
        return true;
    }
}