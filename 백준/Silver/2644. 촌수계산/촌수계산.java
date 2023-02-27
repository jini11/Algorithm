import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, from, to;
	static int[][] map;
	static boolean[] visited;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		from = Integer.parseInt(st.nextToken());
		to = Integer.parseInt(st.nextToken());
		
		int E = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = map[b][a] = 1;
		}
		
		dfs(from, 0);
		
		if (res == 0) {
			System.out.println(-1);
		} else {
			System.out.println(res);
		}
	}
	
	private static void dfs(int from, int cnt) {
		if (from == to) {
			res = cnt;
			return;
		}
		visited[from] = true;
		
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && map[from][i] == 1) {
				dfs(i, cnt + 1);
			}
		}
	}
}