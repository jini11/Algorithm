import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, max;
	static char[][] map;
	static boolean[][] visited;

	static final int[] dr = {1, -1, 0, 0};
	static final int[] dc = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') {
					visited = new boolean[N][M];
					bfs(i, j, visited);
				}
			}
		}

		System.out.println(max);
	}

	private static void bfs(int row, int col, boolean[][] visited) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {row, col, 0});
		visited[row][col] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			max = Math.max(max, cur[2]);
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr < 0  || nc < 0 || nr >= N || nc >= M) continue;
				if (visited[nr][nc] || map[nr][nc] == 'W') continue;

				visited[nr][nc] = true;
				queue.add(new int[] {nr, nc, cur[2] + 1});
			}
		}
	}
}