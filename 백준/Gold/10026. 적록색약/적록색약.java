import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, cnt, cnt2;
	static char[][] map;
	static boolean[][] visited;
	static final int[] dr = { 1, -1, 0, 0 };
	static final int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(map[i][j], i, j);
					cnt++;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'R') {
					map[i][j] = 'G';
				}
			}
		}

		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(map[i][j], i, j);
					cnt2++;
				}
			}
		}
		
		System.out.println(cnt + " " + cnt2);
	}

	private static void dfs(char word, int row, int col) {
		visited[row][col] = true;

		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
			if (visited[nr][nc] || map[nr][nc] != word) continue;
			
			dfs(word, nr, nc);
		}
	}
}