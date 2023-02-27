import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int W, H;
	static int[][] map;
	static boolean[][] visited;
	static int res;

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			if (W == 0 && H == 0) {
				break;
			}

			map = new int[H][W];
			visited = new boolean[H][W];
			res = 0;
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (!visited[i][j] && map[i][j] == 1) {
						dfs(i, j);
						res++;
					}
				}
			}
			sb.append(res).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void dfs(int row, int col) {
		visited[row][col] = true;

		for (int i = 0; i < 8; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			if (nr < 0 || nr >= H || nc < 0 || nc >= W)
				continue;
			if (!visited[nr][nc] && map[nr][nc] == 1) {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
}