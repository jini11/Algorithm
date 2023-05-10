import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static int res;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static class Point {
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			if (!movePeople()) {
				break;
			}
			res++;
		}
		
		System.out.println(res);
	}
	
	private static boolean movePeople() {
		visited = new boolean[N][N];
		int cnt = 0;
		int go = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				List<Point> people = new ArrayList<>();
				if (!visited[i][j]) {
					go = bfs(i, j, people);
					if (go == 0) {
						cnt++;
					} else {
						people.add(new Point(i, j));
						moveNow(people, go);
					}
				}
			}
		}
		if (cnt == N * N) {
			return false;
		}
		
		return true;
	}
	
	private static int bfs(int row, int col, List<Point> people) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(row, col));
		visited[row][col] = true;
		int sum = map[row][col];
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.row + dr[i];
				int nc = cur.col + dc[i];
				int num = map[cur.row][cur.col];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) {
					continue;
				}
			
				int distance = Math.abs(num - map[nr][nc]);
				if (distance >= L && distance <= R) {
					queue.offer(new Point(nr, nc));
					visited[nr][nc] = true;
					people.add(new Point(nr, nc));
					sum += map[nr][nc];
				}
			}
		}
		
		if (people.size() == 0) {
			return 0;
		}
		return sum;
	}
	
	private static void moveNow(List<Point> people, int go) {
		int avg = go / people.size();
		for (Point person : people) {
			map[person.row][person.col] = avg;
		}
	}
}