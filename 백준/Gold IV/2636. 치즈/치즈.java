import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int time, cheese, leftCheese;

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static class Point {
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					cheese++;
				}
			}
		}
		
		while (cheese != 0) {
			time++;
			leftCheese = cheese;
			visited = new boolean[N][M];
			bfs(0, 0);
		}
		
		System.out.println(time);
		System.out.println(leftCheese);
	}
	
	private static void bfs(int startRow, int startCol) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(startRow, startCol));
		visited[startRow][startCol] = true;
		
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.row + dr[i];
				int nc = cur.col + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				if (visited[nr][nc]) {
					continue;
				}
				visited[nr][nc] = true;
				if (map[nr][nc] == 0) {
					queue.add(new Point(nr, nc));
				} else if (map[nr][nc] == 1) {
					cheese--;
					map[nr][nc] = 0;
				}
			}
		}
	}
}