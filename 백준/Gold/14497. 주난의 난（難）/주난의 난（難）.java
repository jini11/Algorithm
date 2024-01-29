import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, res;
	static int[][] map;
	static List<int[]> emptyList;
	static int startRow, startCol, endRow, endCol;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		startRow = Integer.parseInt(st.nextToken()) - 1;
		startCol = Integer.parseInt(st.nextToken()) - 1;
		endRow = Integer.parseInt(st.nextToken()) - 1;
		endCol = Integer.parseInt(st.nextToken()) - 1;
		
		map = new int[N][M];
		emptyList = new ArrayList<int[]>();
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
				if (map[i][j] == -13 || map[i][j] == -6) {
					map[i][j] = 0;
				}
			}
		}
		
		while (true) {
			if (findChoco()) {
				break;
			}
			wave();
			res++;
		}
		System.out.println(res + 1);
	}
	
	private static boolean findChoco() {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[N][M];
		queue.add(new int[] { startRow, startCol});
		emptyList.add(new int[] { startRow, startCol });
		visited[startRow][startCol] = true;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (cur[0] == endRow && cur[1] == endCol) {
				return true;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) continue;
				if (map[nr][nc] == 0) {
					emptyList.add(new int[] { nr, nc });
					queue.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
		
		return false;
	}
	
	private static void wave() {
		boolean[][] visited = new boolean[N][M];
		for (int[] empty : emptyList) {
			for (int i = 0; i < 4; i++) {
				int nr = empty[0] + dr[i];
				int nc = empty[1] + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if (!visited[nr][nc] && map[nr][nc] == 1) {
					map[nr][nc] = 0;
					continue;
				}
				visited[nr][nc] = true;
			}
		}
	}
	
	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}