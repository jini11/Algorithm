import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int V;
	static List<Edge> edgeList;
	static int[] parents;

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	private static void makeSet() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}

	private static int findSet(int v) {
		if (parents[v] == v) {
			return v;
		}
		return parents[v] = findSet(parents[v]);
	}

	private static boolean union(int u, int v) {
		int uRoot = findSet(u);
		int vRoot = findSet(v);

		if (uRoot == vRoot) {
			return false;
		}
		parents[vRoot] = uRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());

		edgeList = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < V; j++) {
				int weight = Integer.parseInt(st.nextToken());
				edgeList.add(new Edge(i, j, weight));
			}
		}

		Collections.sort(edgeList);

		makeSet();
		long res = 0;
		int cnt = 0;

		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				res += edge.weight;
				if (++cnt == V - 1)
					break;
			}
		}
		System.out.println(res);
	}
}