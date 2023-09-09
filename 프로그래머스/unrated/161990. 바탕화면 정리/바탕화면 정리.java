class Solution {
    static int R, C;
    static char[][] map;
    
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        
        R = wallpaper.length;
        C = wallpaper[0].length();
        map = new char[R][C];
        
        int minRow = R;
        int minCol = C;
        int maxRow = 0;
        int maxCol = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = wallpaper[i].charAt(j);
                if (map[i][j] == '#') {
                    minRow = Math.min(i, minRow);
                    minCol = Math.min(j, minCol);
                    maxRow = Math.max(i, maxRow);
                    maxCol = Math.max(j, maxCol);
                }
            }
        }
        
        answer = new int[] {minRow, minCol, maxRow + 1, maxCol + 1};
        return answer;
    }
}