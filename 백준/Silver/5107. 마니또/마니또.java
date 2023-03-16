import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int res;
	static int N;
	static int[][] graph;
	static boolean[] visited;
	static HashMap<String, Integer> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = 1;
		while (true) {
			res = 0;
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			map = new HashMap<>();
			
			graph = new int[N + 1][N + 1];
			visited = new boolean[N + 1];
			int idx = 1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String from = st.nextToken();
				String to = st.nextToken();
				if (!map.containsKey(from)) {
					map.put(from, idx++);
				}
				if (!map.containsKey(to)) {
					map.put(to, idx++);
				}
				int fromIdx = map.get(from);
				int toIdx = map.get(to);
				graph[fromIdx][toIdx] = graph[toIdx][fromIdx] = 1;
			}

			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					bfs(i);
					res++;
				}
			}

			sb.append(tc).append(" ").append(res).append("\n");
			tc++;
		}
		System.out.println(sb.toString());
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		visited[start] = true;
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (graph[cur][i] != 0 && !visited[i]) {
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
	}
}