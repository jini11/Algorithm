import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] adjList;
	static int V, E, cnt;
	static int[] res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[V + 1];
		res = new int[V + 1];
		for (int i = 0; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adjList[from].add(to);
			adjList[to].add(from);
		}

		for (int i = 1; i <= V; i++) {
			Collections.sort(adjList[i], Collections.reverseOrder());
		}

		cnt = 1;
		dfs(start);

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			sb.append(res[i] + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int node) {
		res[node] = cnt++;

		for (int i = 0; i < adjList[node].size(); i++) {
			int next = adjList[node].get(i);
			if (res[next] == 0) {
				dfs(next);
			}
		}
	}
}