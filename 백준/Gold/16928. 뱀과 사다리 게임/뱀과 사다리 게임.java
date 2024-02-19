import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] map = new int[101];
	static int[] snakeAndLadder = new int[101];
	static boolean[] visited = new boolean[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			snakeAndLadder[from] = to;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			snakeAndLadder[from] = to;
		}

		bfs();
	}

	private static void bfs() {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(1);
		map[1] = 0;
		visited[1] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (cur == 100) {
				System.out.println(map[100]);
				return;
			}

			for (int i = 1; i < 7; i++) {
				int next = cur + i;
				if (next > 100)
					continue;
				if (visited[next])
					continue;

				visited[next] = true;

				if (snakeAndLadder[next] != 0) {
					if (!visited[snakeAndLadder[next]]) {
						queue.add(snakeAndLadder[next]);
						visited[snakeAndLadder[next]] = true;
						map[snakeAndLadder[next]] = map[cur] + 1;
					}
				} else {
					queue.add(next);
					map[next] = map[cur] + 1;
				}
			}
		}
	}
}