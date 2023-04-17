import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Edge> edgeList;
	static int[] parents;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Edge implements Comparable<Edge> {
		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.weight > o.weight) {
				return 1;
			}
			return -1;
		}
	}

	private static void makeSet() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edgeList = new ArrayList<>();
		Point[] points = new Point[N];

		makeSet();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points[i] = new Point(x, y);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			union(from - 1, to - 1);
		}

		for (int i = 0; i < N - 1; i++) {
			Point point1 = points[i];
			for (int j = i + 1; j < N; j++) {
				Point point2 = points[j];
				double weight = Math.sqrt(Math.pow(Math.abs(point1.x - point2.x), 2) + Math.pow(Math.abs(point1.y - point2.y), 2));
				edgeList.add(new Edge(i, j, weight));
			}
		}
		
		Collections.sort(edgeList);
		
		double res = 0;
		int cnt = 0;
		
		for (Edge edge:edgeList) {
			if (union(edge.from , edge.to)) {
				res += edge.weight;
				if (++cnt == N-1) {
					break;
				}
			}
		}
		System.out.printf("%.2f", res);
	}
}