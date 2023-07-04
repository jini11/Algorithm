import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static char[][] map;
	static int ans, N;
	static final int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static final int[] dc = {0, 0, -1, 1, 1, -1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			ans = 0;
			
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			findBomb();
			
			System.out.println("#" + t + " " + ans);
		}
	}
	
	private static void findBomb() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != '.') continue;
				if (checkAround(i, j)) {
					bfs(i, j);
					ans++;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '.') {
					ans++;
				}
			}
		}
	}
	
	private static boolean checkAround(int r, int c) {
		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
			if (map[nr][nc] == '*') return false;
		}
		return true;
	}
	
	private static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		map[r][c] = '-';
		queue.add(new int[] {r, c});
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			map[cur[0]][cur[1]] = '-';
			for (int i = 0; i < 8; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] != '.') continue;
				if (checkAround(nr, nc)) {
					queue.add(new int[] {nr, nc});
				}
				map[nr][nc] = '-';
			}
		}
	}
}