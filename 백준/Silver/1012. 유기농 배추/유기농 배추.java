import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int N, M, K, bug;
	static final int[] dr = {1, -1, 0, 0};
	static final int[] dc = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				map[row][col] = 1;
			}

			bug = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j< M; j++) {
					if (map[i][j] != 0) {
						bfs(i, j);
						bug++;
					}
				}
			}

			sb.append(bug + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void bfs(int row, int col) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { row, col });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 0) continue;

				map[nr][nc] = 0;
				queue.add(new int[] {nr, nc});
			}
		}
	}
}