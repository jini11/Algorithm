import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int res;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static List<Edge> edgeList;
	static int[] parents;

	static class Point {
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return weight + "";
		}

	}

	static void makeSet(int n) {
		parents = new int[n+1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (a == parents[a]) {
			return a;
		}

		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot) {
			return false;
		}

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					map[i][j] = -1;
			}
		}

		// 각 섬에 인덱스 부여
		int bridge = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == -1) {
					map[i][j] = bridge;
					checkArea(i, j, bridge++);
				}
			}
		}
		bridge--;
		edgeList = new ArrayList<>();

		// 가능한 모든 다리 연결
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && map[i][j] != 9) {
					makeBridge(i, j);
				}
			}
		}
		makeSet(bridge);
		
		Collections.sort(edgeList);

		int result = 0, count = 0;

		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				result += edge.weight;
				if (++count == bridge - 1)
					break;
			}
		}
		if (count != bridge - 1) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}

	private static void makeBridge(int row, int col) {

		for (int i = 0; i < 4; i++) {
			if (canMake(row, col, i, false)) {
				canMake(row, col, i, true);
			}
		}
	}

	private static boolean canMake(int row, int col, int d, boolean make) {
		int len = 0;
		int nr = row;
		int nc = col;
		while (true) {
			nr += dr[d];
			nc += dc[d];

			if (nr < 0 || nc < 0 || nr >= N || nc >= M)
				return false;

			if (map[nr][nc] > 0 && map[nr][nc] != 9) {
				if (len >= 2) {
					if (make) {
//						System.out.println(map[row][col] + " -> " + map[nr][nc] + " : " + len);
						edgeList.add(new Edge(map[row][col], map[nr][nc], len));
					}
					return true;
				}
				return false;
			}
			if (map[nr][nc] == map[row][col])
				return false;
			if (make) {
				map[nr][nc] = 9;
			}
			len++;
		}
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}

	private static void checkArea(int startRow, int startCol, int idx) {
		Queue<Point> round = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		round.add(new Point(startRow, startCol));

		while (!round.isEmpty()) {
			Point cur = round.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.row + dr[i];
				int nc = cur.col + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || map[nr][nc] == 0) {
					continue;
				}
				map[nr][nc] = idx;
				round.add(new Point(nr, nc));
				visited[nr][nc] = true;
			}
		}
	}
}