import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 999999999;
	static int V, E;
	static int[][] map;
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		map = new int[V+1][V+1];
		res = INF;
		
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i != j) {
					map[i][j] = INF;
				}
			}
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			map[from][to] = cost;
		}
		
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j) continue;
				if (map[i][j] != INF && map[j][i] != INF) {
					res = Math.min(res, map[i][j] + map[j][i]);
				}
			}
		}
		
		if (res == INF) {
			System.out.println(-1);
		} else {
			System.out.println(res);
		}
	}
}