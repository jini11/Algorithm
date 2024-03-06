import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int M, N, K, cnt;
	static boolean[][] map;
	static List<Integer> res;
	
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M];
		res = new ArrayList<Integer>();
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int startRow = Integer.parseInt(st.nextToken());
			int startCol = Integer.parseInt(st.nextToken());
			int endRow = Integer.parseInt(st.nextToken());
			int endCol = Integer.parseInt(st.nextToken());
			drawSquare(startRow, startCol, endRow, endCol);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!map[i][j]) {
					res.add(bfs(i, j));
				}
			}
		}
		
		Collections.sort(res);
		System.out.println(res.size());
		for (Integer sum : res) {
			System.out.print(sum + " ");
		}
	}
	
	private static void drawSquare(int startRow, int startCol, int endRow, int endCol) {
		for (int i = startRow; i < endRow; i++) {
			for (int j = startCol; j < endCol; j++) {
				map[i][j] = true;
			}
		}
	}
	
	private static int bfs(int row, int col) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {row, col});
		map[row][col] = true;
		int sum = 1;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc]) continue;
				
				queue.add(new int[] {nr, nc});
				map[nr][nc] = true;
				sum++;
			}
		}
		
		return sum;
	}
}