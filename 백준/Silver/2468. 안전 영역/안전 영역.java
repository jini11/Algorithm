import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int res;

	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		res = 1;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
			}
		}

		for (int height = min; height <= max; height++) {
			int[][] copyMap = duplicateMap();
			copyMap = rainDrop(copyMap, height);
			res = Math.max(res, countSafetyZone(copyMap));
		}

		System.out.println(res);
	}

	private static int[][] duplicateMap() {
		int[][] copy = new int[N][N];
		for (int i = 0; i < N; i++) {
			copy[i] = map[i].clone();
		}
		return copy;
	}

	private static int[][] rainDrop(int[][] nmap, int height) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (nmap[i][j] <= height) {
					nmap[i][j] = -1;
				}
			}
		}
		return nmap;
	}

	private static int countSafetyZone(int[][] nmap) {
		boolean[][] visited = new boolean[N][N];
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (nmap[i][j] != -1 && !visited[i][j]) {
					dfs(i, j, visited, nmap);
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void dfs(int row, int col, boolean[][] visited, int[][] nmap) {
		visited[row][col] = true;

		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			if (!visited[nr][nc] && nmap[nr][nc] != -1) {
				dfs(nr, nc, visited, nmap);
			}
		}
	}
}