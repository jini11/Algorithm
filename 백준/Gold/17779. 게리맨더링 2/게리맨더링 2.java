import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] map2;
	static int[] ans;
	static int total;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		ans = new int[4];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}

		res = Integer.MAX_VALUE;
		combi(0);

		System.out.println(res);
	}

	private static void combi(int cnt) {
		if (cnt == 4) {
			if (ans[0] + ans[2] + ans[3] > N) {
				return;
			}
			if (ans[1] - ans[2] < 1 || ans[1] + ans[3] > N) {
				return;
			}

			int population = calcPopulation();
			res = Math.min(res, population);
			return;
		}

		for (int i = 1; i <= N; i++) {
			ans[cnt] = i;
			combi(cnt + 1);
		}
	}

	private static int calcPopulation() {
		map2 = new boolean[N + 1][N + 1];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int sum = 0;

		int x = ans[0];
		int y = ans[1];
		int d1 = ans[2];
		int d2 = ans[3];

		for (int i = 0; i <= d1; i++) {
			map2[x + i][y - i] = true;
			map2[x + d2 + i][y + d2 - i] = true;
		}
		for (int i = 0; i <= d2; i++) {
			map2[x + i][y + i] = true;
			map2[x + d1 + i][y - d1 + i] = true;
		}

		int tmp = getSum(1, x + d1 - 1, 1, y);
		max = Math.max(max, tmp);
		min = Math.min(min, tmp);
		sum += tmp;

		tmp = getSum2(1, x + d2, y + 1, N);
		max = Math.max(max, tmp);
		min = Math.min(min, tmp);
		sum += tmp;

		tmp = getSum(x + d1, N, 1, y - d1 + d2 - 1);
		max = Math.max(max, tmp);
		min = Math.min(min, tmp);
		sum += tmp;

		tmp = getSum2(x + d2 + 1, N, y - d1 + d2, N);
		max = Math.max(max, tmp);
		min = Math.min(min, tmp);
		sum += tmp;

		tmp = total - sum;
		max = Math.max(max, tmp);
		min = Math.min(min, tmp);

		return max - min;
	}

	private static int getSum(int startR, int endR, int startC, int endC) {
		int sum = 0;
		for (int r = startR; r <= endR; r++) {
			for (int c = startC; c <= endC; c++) {
				if (map2[r][c])
					break;
				sum += map[r][c];
			}
		}
		return sum;
	}

	private static int getSum2(int startR, int endR, int startC, int endC) {
		int sum = 0;
		for (int r = startR; r <= endR; r++) {
			for (int c = endC; c >= startC; c--) {
				if (map2[r][c])
					break;
				sum += map[r][c];
			}
		}
		return sum;
	}
}