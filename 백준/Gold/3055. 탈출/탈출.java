import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
	static int R, C;
	static char[][] map;
	static Point start;
	static int res = Integer.MAX_VALUE;
	static boolean[][] visitGo;
	static boolean[][] visitWater;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class Point {
		int row, col, cnt;

		public Point(int row, int col, int cnt) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'S') {
					start = new Point(i, j, 0);
					map[i][j] = '.';
				}
			}
		}
		visitGo = new boolean[R][C];
		visitWater = new boolean[R][C];
		bfs();

		if (res == Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(res);
		}
	}

	private static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(start);

		while (!queue.isEmpty()) {
			waterFall();
			for (int size = 0; size < queue.size(); size++) {
				Point cur = queue.poll();
				if (map[cur.row][cur.col] == 'D') {
					res = cur.cnt;
					return;
				}
				for (int i = 0; i < 4; i++) {
					int nr = cur.row + dr[i];
					int nc = cur.col + dc[i];

					if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == '*' || map[nr][nc] == 'X')
						continue;
					if (visitGo[nr][nc] || visitWater[nr][nc])
						continue;

					queue.add(new Point(nr, nc, cur.cnt + 1));
					visitGo[nr][nc] = true;
				}
			}
		}
	}

	private static void waterFall() {
		Queue<Point> water = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '*') {
					water.add(new Point(i, j, 0));
				}
			}
		}
		while (!water.isEmpty()) {
			Point curWater = water.poll();
			for (int i = 0; i < 4; i++) {
				int nr = curWater.row + dr[i];
				int nc = curWater.col + dc[i];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 'D' || map[nr][nc] == 'X')
					continue;
				if (visitWater[nr][nc])
					continue;
				
				map[nr][nc] = '*';
				visitWater[nr][nc] = true;
			}
		}
	}
}