import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T, dustCnt;
	static int[][] map, secondMap;
	static int airconRow, airconCol;
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					airconRow = i;
					airconCol = j;
				}
			}
		}
		
		for (int t = 0; t < T; t++) {
			spreadDust();
			runAirCon();
		}
		
		calcDust();
		
		System.out.println(dustCnt);
	}
	
	private static void spreadDust() {
		int[][] secondMap = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					int spread = bfs(i, j, secondMap);
					if (spread > 0)
						map[i][j] -= (map[i][j] / 5 * spread);
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1) continue;
				map[i][j] += secondMap[i][j]; 
			}
		}
	}
	
	private static int bfs(int row, int col, int[][] secondMap) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == -1) continue;
			secondMap[nr][nc] += (map[row][col] / 5);
			cnt++;
		}
		return cnt;
	}
	
	private static void runAirCon() {
		int row = airconRow - 1;
		int col = airconCol;
		int rowDir = -1;
		int colDir = 0;
		
		// up
		while (true) {
			int nr = row + rowDir;
			int nc = col + colDir;
			
			if (nr == 0 && nc == 0) {
				rowDir = 0;
				colDir = 1;
			} else if (nr == 0 && nc == C - 1) {
				rowDir = 1;
				colDir = 0;
			} else if (nr == airconRow - 1 && nc == C - 1) {
				rowDir = 0;
				colDir = -1;
			}
			
			map[row][col] = map[nr][nc];
			
			row = nr;
			col = nc;
			
			if (row == airconRow - 1 && col == airconCol) {
				break;
			}
		}
		
		map[airconRow - 1][airconCol] = -1;
		map[airconRow - 1][airconCol + 1] = 0;
		
		// down
		row = airconRow;
		col = airconCol;
		rowDir = 1;
		colDir = 0;
		
		while (true) {
			int nr = row + rowDir;
			int nc = col + colDir;
			
			if (nr == R - 1 && nc == 0) {
				rowDir = 0;
				colDir = 1;
			} else if (nr == R - 1 && nc == C - 1) {
				rowDir = -1;
				colDir = 0;
			} else if (nr == airconRow && nc == C - 1) {
				rowDir = 0;
				colDir = -1;
			}
			
			map[row][col] = map[nr][nc];
			
			row = nr;
			col = nc;
			
			if (row == airconRow && col == airconCol) {
				break;
			}
		}
		
		map[airconRow][airconCol] = -1;
		map[airconRow][airconCol + 1] = 0;
	}
	
	private static void calcDust() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1) continue;
				dustCnt += map[i][j];
			}
		}
	}
}