import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge> {
		int v, weight;

		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		List<Edge>[] adj = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, weight));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1;
		int end = Integer.parseInt(st.nextToken()) - 1;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[V];
		Edge[] d = new Edge[V];

		for (int i = 0; i < V; i++) {
			if (i == start) {
				d[i] = new Edge(i, 0);
			} else {
				d[i] = new Edge(i, Integer.MAX_VALUE);
			}
		}
		pq.add(d[start]);

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			for (Edge next : adj[cur.v]) {
				if (!check[next.v] && d[next.v].weight > d[cur.v].weight + next.weight) {
					d[next.v].weight = d[cur.v].weight + next.weight;
					pq.remove(d[next.v]);
					pq.add(d[next.v]);
				}
			}
			check[cur.v] = true;
		}
		System.out.println(d[end].weight);
	}
}