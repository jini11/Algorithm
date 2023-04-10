import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, X;
	static List<Edge>[] adj;
	static Edge[][] d;
	static int res;
	
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

		@Override
		public String toString() {
			return weight +	"";
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, weight));
		}
		
		d = new Edge[N][N];
		for (int i = 0; i < N; i++) {
			dijkstra(i);
		}
		
		for (int i = 0; i < N; i++) {
			if (d[i][X].weight == 0 || d[X][i].weight == 0) continue;
			res = Math.max(res, d[i][X].weight + d[X][i].weight);
		}
		System.out.println(res);
	}
	private static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			if (i == start) {
				d[start][i] = new Edge(i, 0);
			} else {
				d[start][i] = new Edge(i, Integer.MAX_VALUE);
			}
			pq.add(d[start][i]);
		}
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			for (Edge next : adj[cur.v]) {
				if (check[next.v]) continue;
				if (d[start][next.v].weight > d[start][cur.v].weight + next.weight) {
					d[start][next.v].weight = d[start][cur.v].weight + next.weight;
					pq.remove(d[start][next.v]);
					pq.add(d[start][next.v]);
				}
			}
			check[cur.v] = true;
		}
	}
}