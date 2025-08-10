import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static boolean[] isSelected;
	static int res = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		isSelected = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		per(0, 0, 0);

		System.out.println(res);
	}

	private static void per(int cur, int cost, int cnt) {
		if (cnt == N - 1) {
			if (map[cur][0] == 0)
				return;
			res = Math.min(res, cost + map[cur][0]);
			return;
		}
		for (int i = 1; i < N; i++) {
			if (isSelected[i] || map[cur][i] == 0)
				continue;
			isSelected[i] = true;
			per(i, cost + map[cur][i], cnt + 1);
			isSelected[i] = false;
		}
	}
}