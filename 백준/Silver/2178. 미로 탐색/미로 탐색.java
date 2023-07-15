import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N, M;
	static int[][] map;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		N = Integer.parseInt(input.split(" ")[0]);
		M = Integer.parseInt(input.split(" ")[1]);

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		bfs(0, 0);
		
		System.out.println(map[N-1][M-1]);
	}

	private static void bfs(int row, int col) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { row, col });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] != 1) continue;
				
				queue.add(new int[] { nr, nc});
				map[nr][nc] += map[cur[0]][cur[1]];
			}
		}
	}
}