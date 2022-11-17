import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution
{
	static int[][] map;
	static int n;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String line = br.readLine();
			n = Integer.parseInt(line.split(" ")[0]);
			int m = Integer.parseInt(line.split(" ")[1]);
			map = new int[n+1][n+1];
			map[n/2][n/2] = 2;
			map[n/2][n/2+1] = 1;
			map[n/2+1][n/2] = 1;
			map[n/2+1][n/2+1] = 2;
			
			for (int i = 0; i < m; i++) {
				line = br.readLine();
				int x = Integer.parseInt(line.split(" ")[0]);
				int y = Integer.parseInt(line.split(" ")[1]);
				int color = Integer.parseInt(line.split(" ")[2]);
				
				map[x][y] = color;
				checkAround(x, y, color);
			}
            int black = 0;
            int white = 0;
            for (int i = 1; i <= n; i++) {
             	for (int j = 1; j <= n; j++) {
                 	if (map[i][j] == 1)
                        black++;
                    else if (map[i][j] == 2) 
                        white++;
                }
            }
            
            System.out.println("#" + test_case + " " + black + " " + white);
		}
	}
	
	public static void checkAround(int x, int y, int color) {
		for (int dx = -1; dx <= 1; dx++) { // 상하좌우 대각선 모두 탐색
			for (int dy = -1; dy <= 1; dy++) {
				if (dx == 0 && dy == 0) {
					continue;
				}
				
				int nx = x + dx;
				int ny = y + dy;
				
				boolean flag = false;
				while (true) {
					if (nx <= 0 || ny <= 0 || nx > n || ny > n || map[nx][ny] == 0) {
						break;
					}
					if (map[nx][ny] == color) {
						flag = true;
						break;
					}
					nx += dx;
					ny += dy;
				}
				
				while (flag) {
					if (nx == x && ny == y) {
						break;
					}
					map[nx][ny] = color;
					nx -= dx;
					ny -= dy;
				}
			}
		}
	}

}