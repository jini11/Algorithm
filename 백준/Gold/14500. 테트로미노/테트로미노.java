import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean[][] visited;
	static int N, M, max;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][M];
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 0, 0);
				visited[i][j] = false;
			}
		}
		
		System.out.println(max);
	}
	
	private static void dfs(int row, int col, int sum, int cnt) {
		if (cnt == 4) {
			max = Math.max(max, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
			
			if (cnt == 2) {
				visited[nr][nc] = true;
				dfs(row, col, sum + map[nr][nc], cnt + 1);
				visited[nr][nc] = false;
			}
			
			visited[nr][nc] = true;
			dfs(nr, nc, sum + map[nr][nc], cnt + 1);
			visited[nr][nc] = false;
		}
	}
	
}