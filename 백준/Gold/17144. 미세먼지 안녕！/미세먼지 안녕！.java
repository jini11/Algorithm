import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
	static int R, C, T;
	static int[][] map;
	static int res;
	static Point aircon;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	static class Point {
		int row, col, amount;

		public Point(int row, int col, int amount) {
			this.row = row;
			this.col = col;
			this.amount = amount;
		}
	}

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
					aircon = new Point(i, j, map[i][j]);
				}
			}
		}

		for (int time = 0; time < T; time++) {
			spreadDust();
			runAircon();
		}

		System.out.println(getDust());
	}

	private static void runAircon() {
		// 위
		int nr = aircon.row - 2;
		int nc = 0;
		int drow = -1;
		int dcol = 0;
		while (true) {
			if (nr == aircon.row - 1 && nc == 1)
				break;
			if (nr == aircon.row - 1 && nc == C - 1) {
				drow = 0;
				dcol = -1;
			} else if (nr == 0 && nc == C - 1) {
				drow = 1;
				dcol = 0;
			} else if (nr == 0 && nc == 0) {
				drow = 0;
				dcol = 1;
			}

			map[nr][nc] = map[nr + drow][nc + dcol];
			nr += drow;
			nc += dcol;
		}
		map[aircon.row - 1][0] = aircon.amount;
		map[aircon.row - 1][1] = 0;
		
		// 아래
		nr = aircon.row + 1;
		nc = 0;
		drow = 1;
		dcol = 0;
		while (true) {
			if (nr == aircon.row && nc == 1)
				break;
			if (nr == R - 1 && nc == 0) {
				drow = 0;
				dcol = 1;
			} else if (nr == R - 1 && nc == C - 1) {
				drow = -1;
				dcol = 0;
			} else if (nr == aircon.row && nc == C - 1) {
				drow = 0;
				dcol = -1;
			}
			map[nr][nc] = map[nr + drow][nc + dcol];
			nr += drow;
			nc += dcol;
		}
		map[aircon.row][0] = aircon.amount;
		map[aircon.row][1] = 0;
	}

	private static void spreadDust() {
		Queue<Point> queue = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0)
					queue.add(new Point(i, j, map[i][j]));
			}
		}

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				int nr = cur.row + dr[i];
				int nc = cur.col + dc[i];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == -1)
					continue;

				cnt++;
				map[nr][nc] += cur.amount / 5;
			}
			map[cur.row][cur.col] -= (cur.amount / 5 * cnt);
		}
	}

	private static int getDust() {
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum += map[i][j];
			}
		}
		return sum + 2;
	}
}