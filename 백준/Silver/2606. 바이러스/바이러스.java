import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, E;
	static int[][] map;
	static boolean[] visited;
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = 1;
		}

		dfs(1);
		
		System.out.println(res);
	}
	
	private static void dfs(int cur) {
		visited[cur] = true;
		
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && map[cur][i] != 0) {
				res++;
				dfs(i);
			}
		}
	}
}