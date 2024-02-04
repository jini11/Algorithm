import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, time;
	static int[][] map, water;
	static boolean[][] visited;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		water = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			int cnt = 0;
			visited = new boolean[N][M];
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					if (map[i][j] != 0 && !visited[i][j]) {
						checkIce(i, j);
						cnt++;
					}
				}
			}
			if (cnt > 1) {
				System.out.println(time);
				break;
			} else if (cnt == 0) {
				System.out.println(0);
				break;
			}
			
			countWater();
			removeIce();
			
			time++;
		}
	}
	
	private static void countWater() {
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				int cnt = 0;
				if (map[i][j] != 0) {
					for (int k = 0; k < 4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if (isOut(nr, nc)) continue;
						if (map[nr][nc] == 0) {
							cnt++;
						}
					}
				}
				water[i][j] = cnt;
			}
		}
	}
	
	private static void removeIce() {
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				if (map[i][j] != 0) {
					map[i][j] = Math.max(0, map[i][j] - water[i][j]);
				}
			}
		}
	}
	
	private static void checkIce(int row, int col) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {row, col});
		visited[row][col] = true;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if (isOut(nr, nc)) continue;
				if (visited[nr][nc] || map[nr][nc] == 0) continue;
				
				queue.add(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
	}
	
	private static boolean isOut(int row, int col) {
		if (row < 0 || row >= N || col < 0 || col >= M)
			return true;
		return false;
	}
}