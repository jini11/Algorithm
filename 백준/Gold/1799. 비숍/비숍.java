import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, black_res, white_res;
	static int[][] map;
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[][] visited = new boolean[N][N];
		black_bishop(visited, 0, 0, 0);
		
		visited = new boolean[N][N];
		white_bishop(visited, 0, 1, 0);
		
		System.out.println(black_res + white_res);
	}
	
	private static void black_bishop(boolean[][] visited, int row, int col, int cnt) {
		
		if (col >= N) {
			row += 1;
			if (row % 2 == 0) {
				col = 0;
			} else {
				col = 1;
			}
		}
		
		if (row >= N) {
			black_res = Math.max(black_res, cnt);
			return;
		}
		
		if (map[row][col] != 0 && canPlace(visited, row, col)) { // 놓을수도
			visited[row][col] = true;
			black_bishop(visited, row, col + 2, cnt + 1);
			visited[row][col] = false;
		}
		// 안 놓을수도
		black_bishop(visited, row, col + 2, cnt);
	}
	
	private static void white_bishop(boolean[][] visited, int row, int col, int cnt) {
		
		if (col >= N) {
			row += 1;
			if (row % 2 == 0) {
				col = 1;
			} else {
				col = 0;
			}
		}
		
		if (row >= N) {
			white_res = Math.max(white_res, cnt);
			return;
		}
		
		if (map[row][col] != 0 && canPlace(visited, row, col)) { // 놓을수도
			visited[row][col] = true;
			white_bishop(visited, row, col + 2, cnt + 1);
			visited[row][col] = false;
		}
		// 안 놓을수도
		white_bishop(visited, row, col + 2, cnt);
	}
	
	private static boolean canPlace(boolean[][] visited, int row, int col) {
		for (int i = 0; i < 4; i++) {
			int nr = row;
			int nc = col;
			while (true) {
				nr += dr[i];
				nc += dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;
				if (visited[nr][nc]) return false;
			}
		}
		return true;
	}
}