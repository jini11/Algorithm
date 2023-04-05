import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, x;
	static int[][] map, map2;
	static int res;
	static boolean[] bridge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			map2 = new int[N][N];
			res = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					map2[j][i] = map[i][j];
				}
			}

			for (int i = 0; i < N; i++) {
				bridge = new boolean[N];
				start(map, i);
				bridge = new boolean[N];
				start(map2, i);
			}
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void start(int[][] map3, int row) {
		boolean isSuccess = false;
		int col = 0;
		int plus = 1;
		while (true) {
			plus = 1;
			if (col == N - 1) {
				isSuccess = true;
				break;
			}
			int d = map3[row][col] - map3[row][col + 1];
			if (d > 1 || d < -1) {
				break;
			} else if (Math.abs(d) == 1) {
				if (checkGround(map3, row, col)) {
					if (d == 1) {
						plus = x;
					}
				} else {
					break;
				}
			}
			col += plus;
			if (col > N - 1) {
				isSuccess = false;
				break;
			}
		}
		if (isSuccess) {
//			System.out.println(row);
			res++;
		}
	}

	private static boolean checkGround(int[][] map3, int row, int col) {
		int nc = col + 1;
		if (map3[row][col] > map3[row][col + 1]) {
			int height = map3[row][nc];
			if (nc + x - 1 >= N)
				return false;
			for (int i = 1; i < x; i++) {
				if (map3[row][nc + i] != height || bridge[nc + i]) {
					return false;
				}
				bridge[nc + i] = true;
			}
		} else {
			int height = map3[row][col];
			if (nc - x < 0) {
				return false;
			}
			for (int i = 1; i <= x; i++) {
				if (map3[row][nc - i] != height || bridge[nc - i]) {
					return false;
				}
				bridge[nc - i] = true;
			}
		}
		return true;
	}
}