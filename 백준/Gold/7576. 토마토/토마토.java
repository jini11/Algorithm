import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N;
	static int[][] farm;
	static int res;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static Queue<int[]> queue = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		farm = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				farm[i][j] = Integer.parseInt(st.nextToken());
				if (farm[i][j] == 1) {
					queue.add(new int[] { i, j });
				}
			}
		}

		makeTomato();

		if (isRipe()) {
			System.out.println(Math.max(0, res - 1));
		} else {
			System.out.println(-1);
		}
	}

	public static void makeTomato() {

		while (!queue.isEmpty()) {
			int[] tomato = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = tomato[0] + dr[i];
				int nc = tomato[1] + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (farm[nr][nc] == -1 || farm[nr][nc] != 0)
					continue;

				farm[nr][nc] = farm[tomato[0]][tomato[1]] + 1;
				queue.add(new int[] { nr, nc });
				res = Math.max(res, farm[nr][nc]);
			}

		}
	}

	private static boolean isRipe() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (farm[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}