import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V, M, res;
	static List<Edge>[] adj;
	static int[] items;
	
	static class Edge implements Comparable<Edge> {
		int v, weight;

		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		items = new int[V];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < V; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		adj = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, weight));
			adj[to].add(new Edge(from, weight));
		}

		for (int i = 0; i < V; i++) {
			res = Math.max(res, dijkstra(i));
		}
		System.out.println(res);
	}
	private static int dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[V];
		Edge[] D = new Edge[V];
		for (int i = 0; i < V; i++) {
			if (i == start) {
				D[i] = new Edge(i, 0);
			} else {
				D[i] = new Edge(i, Integer.MAX_VALUE);
			}
		}
		pq.add(D[start]);
		
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			for (Edge next : adj[edge.v]) {
				if (!check[next.v] && D[next.v].weight > D[edge.v].weight + next.weight) {
					D[next.v].weight = D[edge.v].weight + next.weight;
					pq.remove(D[next.v]);
					pq.add(D[next.v]);
				}
			}
			check[edge.v] = true;
		}
		
		int sum = 0;
		for (int i = 0; i < V; i++) {
			if (D[i].weight <= M) {
				sum += items[i];
			}
		}
		return sum;
	}
}