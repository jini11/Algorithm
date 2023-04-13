import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int V, E;
	static Edge[] edgeList;
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
	
	private static boolean union(int u , int v) {
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
		E = Integer.parseInt(br.readLine());
		
		edgeList = new Edge[E];
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList);
		
		makeSet();
		int res = 0, cnt = 0;
		
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				res += edge.weight;
				if (++cnt == V - 1) break;
			}
		}
		System.out.println(res);
	}
}