import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, res;
	static int[][] map;
	static int small, tall;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map[from][to] = 1;
		}

		res = 0;
		for (int node = 1; node <= N; node++) {
			small = 0;
			tall = 0;

			smaller(node);

			taller(node);

			if (small + tall == N - 1) {
				res++;
			}
		}

		System.out.println(res);
	}

	private static void smaller(int now) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[N + 1];
		queue.add(now);
		visited[now] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int next = 1; next <= N; next++) {
				if (map[next][cur] != 0 && !visited[next]) {
					queue.add(next);
					visited[next] = true;
					small++;
				}
			}
		}
	}

	private static void taller(int now) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[N + 1];
		queue.add(now);
		visited[now] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int next = 1; next <= N; next++) {
				if (map[cur][next] != 0 && !visited[next]) {
					queue.add(next);
					visited[next] = true;
					tall++;
				}
			}
		}
	}
}