import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W, res;
	static char[][] map;
	static int[][] laser;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];

		laser = new int[2][2];
		int idx = 0;
		for (int i = 0; i < H; i++) {
			String input = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'C') {
					laser[idx][0] = i;
					laser[idx++][1] = j;
				}
			}
		}
		
		bfs();
		
		System.out.println(res);
	}
	
	private static void bfs() {
		Deque<int[]> deque = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[H][W];
		
		deque.add(new int[] {laser[0][0], laser[0][1], -1});
		visited[laser[0][0]][laser[0][1]] = true;
		
		while (!deque.isEmpty()) {
			int[] cur = deque.pollFirst();
			if (cur[0] == laser[1][0] && cur[1] == laser[1][1]) {
				res = cur[2];
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0];
				int nc = cur[1];
				while (true) {
					nr += dr[i];
					nc += dc[i];
					
					if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == '*') break;
					if (visited[nr][nc]) continue;
					visited[nr][nc] = true;
					deque.add(new int[] {nr, nc, cur[2] + 1});
				}
			}
		}
		
	}
}