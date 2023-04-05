import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Pos start;
	static Pos[] store;
	static boolean[] visited;
	
	static class Pos {
		int row, col;
		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			store = new Pos[N+1];
			visited = new boolean[N+1];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				store[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			store[N] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			if (bfs()) {
				sb.append("happy\n");
			} else {
				sb.append("sad\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	private static boolean bfs() {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(start);
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			if (cur.row == store[N].row && cur.col == store[N].col) {
				return true;
			}
			for (int i = 0; i <= N; i++) {
				if (!visited[i] && Math.abs(cur.row - store[i].row) + Math.abs(cur.col - store[i].col) <= 1000) {
					visited[i] = true;
					queue.add(store[i]);
				}
			}
		}
		return false;
	} 
}