import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, min, sum;
	static int[][] map;
	static int[] visited;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = 1;
		}

		min = Integer.MAX_VALUE;
		int res = 0;
		for (int i = 1; i <= N; i++) {
			visited = new int[N + 1];
			sum = 0;
			bfs(i);
			if (min > sum) {
				min = sum;
				res = i;
			}
		}
		System.out.println(res);
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(start);
		visited[start] = 1;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int i = 1; i <= N; i++) {
				if (map[cur][i] == 1 && visited[i] < 2) {
					queue.add(i);
					visited[i] = visited[cur] + 1;
					sum += visited[i];
				}
			}
		}
	}
}