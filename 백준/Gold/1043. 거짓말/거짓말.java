import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parents;

	private static void makeSet() {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
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

		if (uRoot > vRoot) {
			parents[uRoot] = vRoot;
		} else {
			parents[vRoot] = uRoot;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		makeSet();

		st = new StringTokenizer(br.readLine());
		boolean[] truth = new boolean[N + 1];
		int num = Integer.parseInt(st.nextToken());
		for (int i = 0; i < num; i++) {
			truth[Integer.parseInt(st.nextToken())] = true;
		}

		int res = 0;
		List<Integer>[] party = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			party[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			int first = Integer.parseInt(st.nextToken());
			party[i].add(first);
			for (int j = 1; j < num; j++) {
				int member = Integer.parseInt(st.nextToken());
				party[i].add(member);
				union(first, member);
				first = member;
			}
		}

		for (int i = 1; i <= N; i++) {
			if (truth[i]) {
				truth[findSet(i)] = true;
			}
		}

		for (int i = 0; i < M; i++) {
			int idx = findSet(party[i].get(0));
			if (!truth[idx])
				res++;
		}
		System.out.println(res);
	}
}