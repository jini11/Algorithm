import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] parents;
	static List<Edge> edgeList;

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
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
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
		parents[uRoot] = vRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int[] well = new int[N + 1];
		edgeList = new ArrayList<>();
		long res = 0;
		for (int i = 1; i <= N; i++) {
			well[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				if (i != j) {
					edgeList.add(new Edge(i, j, weight));
				} else {
					edgeList.add(new Edge(0, i, well[i]));
				}
			}
		}

		Collections.sort(edgeList);

		makeSet();

		int cnt = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				res += edge.weight;
				if (++cnt == N) {
					break;
				}
			}
		}
		System.out.println(res);
	}
}