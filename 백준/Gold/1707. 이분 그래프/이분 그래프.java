import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K, V, E;
	static boolean flag;
	static List<Integer>[] list;
	static boolean[] visited;
	static int[] color;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < K; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			list = new ArrayList[V + 1];
			visited = new boolean[V + 1];
			color = new int[V + 1];
			flag = true;
			
			for (int i = 1; i < V + 1; i++) {
				list[i] = new ArrayList<Integer>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				list[u].add(v);
				list[v].add(u);
			}
			
			for (int i = 1; i < V + 1; i++) {
				if (!flag) break;
				if (color[i] == 0) {
//					bfs(i);
					dfs(i, 1);
				}
			}
			
			if (flag) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			
		}
	}
	
	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start] = true;
		color[start] = 1;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			if (!flag) break;
			for (Integer node : list[cur]) {
				if (visited[node]) continue;
				if (color[node] == 0) {
					color[node] = -color[cur];
					queue.add(node);
				} else if (color[node] + color[cur] != 0) {
					flag = false;
					break;
				}
				visited[cur] = true;
			}
		}
	}
	
	private static void dfs(int node, int col) {
		if (!flag) {
			return;
		}
		
		color[node] = col;
		
		for (Integer next : list[node]) {
			if (color[next] == 0) {
				dfs(next, -col);
			} else if (color[next] == col) {
				flag = false;
				return;
			}
		}
		
	}
}