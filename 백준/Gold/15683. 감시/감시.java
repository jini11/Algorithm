import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, cctvSize;
	static int[][] map;
	static Point[] cctv = new Point[8];
	static int res;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][][] cctvDir = { { {0 }, { 1 }, { 2 }, { 3 } }, 						
								{ { 0, 2 }, { 1, 3 } }, 								
								{ { 0, 1 }, { 1, 2 }, { 2, 3 }, { 0, 3 } }, 			
								{ { 0, 1, 3 }, { 0, 1, 2 }, { 1, 2, 3 }, { 0, 2, 3 } }, 
								{ { 0, 1, 2, 3 } } }; 									

	static class Point {
		int num, row, col;

		public Point(int num, int row, int col) {
			this.num = num;
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
				if (map[i][j] > 0 && map[i][j] < 6) {
					cctv[cctvSize++] = new Point(map[i][j], i, j);
				}
			}
		}
		res = Integer.MAX_VALUE;

		permutation(0, map);

		System.out.println(res);
	}

	private static void permutation(int depth, int[][] map) {
		if (depth == cctvSize) {
			res = Math.min(res, countArea(map));
			return;
		}
		int cctvType = cctv[depth].num - 1;
		int row = cctv[depth].row;
		int col = cctv[depth].col;

		for (int i = 0; i < cctvDir[cctvType].length; i++) {
			int[][] copy = copyMap(map);
			for (int j = 0; j < cctvDir[cctvType][i].length; j++) {
				int dir = cctvDir[cctvType][i][j];
				int nr = row + dr[dir];
				int nc = col + dc[dir];

				while (true) {
					if (nr < 0 || nc < 0 || nr >= N || nc >= M)
						break;
					if (copy[nr][nc] == 6)
						break;
					copy[nr][nc] = -1;
					nr += dr[dir];
					nc += dc[dir];
				}
			}
			permutation(depth + 1, copy);
		}

	}

	private static int[][] copyMap(int[][] map) {
		int[][] copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			copy[i] = map[i].clone();
		}
		return copy;
	}

	private static int countArea(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}