import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, maxConnect, minLen;
	static int[][] map;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static List<Point> cores;

	static class Point {
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if (i == 0 || j == 0 || i == N - 1 || j == N - 1)
							continue;
						cores.add(new Point(i, j));
					}
				}
			}

			maxConnect = 0;
			minLen = Integer.MAX_VALUE;

			subset(0, 0);

			sb.append("#").append(t).append(" ").append(minLen).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void subset(int cnt, int coreCnt) {
		if (cnt == cores.size()) {
			// 최대값 갱신
			if (maxConnect < coreCnt) {
				maxConnect = coreCnt;
				minLen = calc();
			} else if (maxConnect == coreCnt) {
				minLen = Math.min(minLen, calc());
			}

			return;
		}

		// 코어를 연결하거나
		for (int i = 0; i < 4; i++) {
			int row = cores.get(cnt).row;
			int col = cores.get(cnt).col;
			
			// 연결할 수 없으면 continue;
			if (!canConnect(i, row, col))
				continue;
			// 연결할 수 있으면 연결
			connect(2, row, col, i);
			// 재귀
			subset(cnt + 1, coreCnt + 1);
			// 원상복구
			connect(0, row, col, i);
		}

		// 코어를 연결하지 않거나
		subset(cnt + 1, coreCnt);

	}

	private static boolean canConnect(int dir, int row, int col) {

		while (true) {
			row += dr[dir];
			col += dc[dir];

			if (row < 0 || col < 0 || row >= N || col >= N) {
				return true;
			}

			if (map[row][col] == 1 || map[row][col] == 2) {
				return false;
			}
		}
	}

	private static void connect(int line, int row, int col, int dir) {
		while (true) {
			row += dr[dir];
			col += dc[dir];

			if (row < 0 || col < 0 || row >= N || col >= N) {
				break;
			}

			map[row][col] = line;
		}
	}

	private static int calc() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					sum++;
				}
			}
		}
		return sum;
	}
	
	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}