import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;
	static int[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int res = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		
		bfs(0, 0);

		if (res == -1) {
			System.out.println(-1);
		} else {
			System.out.println(res);
		}
	}

	private static void bfs(int row, int col) {
		Queue<int[]> queue = new ArrayDeque<>();
		visited[row][col] = 0;
		queue.offer(new int[] { row, col, 1, 0 }); // row, col, cnt, chance
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (cur[0] == N - 1 && cur[1] == M - 1) {	// 도착지에 도착하면 
				res = cur[2];
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				int cnt = cur[2];
				int chance = cur[3];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (visited[nr][nc] <= chance) continue;	// 더 이상 앞으로 가지 못할 때
				
				if (map[nr][nc] == 1 && chance == 0) {
					visited[nr][nc] = cur[3] + 1;
					queue.offer(new int[] { nr, nc, cnt + 1, chance + 1 });
				} else if (map[nr][nc] == 0) {
					visited[nr][nc] = cur[3];
					
					queue.offer(new int[] { nr, nc, cnt + 1, chance });
				}
			}
		}
	}
}