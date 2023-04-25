import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int V, E;
	static int[] parents;
	static Edge[] edgeList;
	
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
		parents = new int[V+1];
		for (int i = 1; i <= V; i++) {
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edgeList = new Edge[E];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList);
		
		makeSet();
		int result = 0, count = 0;
		
		for (Edge edge: edgeList) {
			if (union(edge.from, edge.to)) {
				result += edge.weight;
				if (++count == V - 1) break;
			}
		}
		System.out.println(result);
	}
}