import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[] isSelected;
	static int res;

	static ArrayList<Integer>[] adj;
	static int[] weight;

	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());

		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine(), " ");
		weight = new int[N];
		for (int i = 0; i < N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}

		for (int from = 0; from < N; from++) {
			st = new StringTokenizer(br.readLine(), " ");
			st.nextToken();
			while (st.hasMoreTokens()) {
				int to = Integer.parseInt(st.nextToken()) - 1;
				adj[from].add(to);
				adj[to].add(from);
			}
		}

		isSelected = new boolean[N];
		res = Integer.MAX_VALUE;
		subset(0);

		if (res == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(res);
		}
	}

	static void makeSet() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int v) {
		if (v == parents[v]) {
			return v;
		}
		return parents[v] = findSet(parents[v]);
	}

	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

//		if (aRoot == bRoot) {
//			return false;
//		}
//		parents[bRoot] = aRoot;
//		return true;
		if (aRoot < bRoot) {
			parents[bRoot] = aRoot;
		} else {
			parents[aRoot] = bRoot;
		}
	}

	private static void subset(int cnt) {
		if (cnt == N) {
			makeSet();
			ArrayList<Integer> blue = new ArrayList<>();
			ArrayList<Integer> red = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					for (int j = 0; j < adj[i].size(); j++) {
						if (isSelected[adj[i].get(j)]) {
							union(i, adj[i].get(j));
						}
					}
					blue.add(i);
				} else {
					for (int j = 0; j < adj[i].size(); j++) {
						if (!isSelected[adj[i].get(j)]) {
							union(i, adj[i].get(j));
						}
					}
					red.add(i);
				}
			}
			if (blue.size() == N || red.size() == N)
				return;

			if (checkSet()) {
				res = Math.min(res, Math.abs(sumSet(blue) - sumSet(red)));
			}
			return;
		}

		isSelected[cnt] = true;
		subset(cnt + 1);

		isSelected[cnt] = false;
		subset(cnt + 1);
	}

	private static boolean checkSet() {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(findSet(i));
		}

		if (set.size() == 2) {
			return true;
		}
		return false;
	}

	private static int sumSet(ArrayList<Integer> list) {
		int sum = 0;
		for (int idx : list) {
			sum += weight[idx];
		}
		return sum;
	}
}