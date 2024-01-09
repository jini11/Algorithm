import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, r, c, d, cnt;
	static int[][] map;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(r, c, d);
		
		System.out.println(cnt);
	}
	
	private static void dfs(int row, int col, int dir) {
		if (map[row][col] == 0) {
			cnt++;
			map[row][col] = 2;
		}
		
		// 청소할 칸이 있는 경우
		for (int i = 0; i < 4; i++) {
			dir -= 1;
			if (dir == -1) dir = 3;
			
			int nr = row + dr[dir];
			int nc = col + dc[dir];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 1) continue;
			if (map[nr][nc] == 0) {
				dfs(nr, nc, dir);
				return;
			}
		}
		
		// 청소할 칸이 없는 경우
		int backDir = (dir + 2) % 4;
		int nr = row + dr[backDir];
		int nc = col + dc[backDir];
		if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 1) return;
		dfs(nr, nc, dir);
		
	}
}