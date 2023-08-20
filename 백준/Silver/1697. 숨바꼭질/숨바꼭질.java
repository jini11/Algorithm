import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, K;
	static int res;
	static boolean[] visited = new boolean[100001];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		bfs(N);

		System.out.println(res);
	}

	private static void bfs(int start) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] { start, 0 });
		visited[start] = true;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (cur[0] == K) {
				res = cur[1];
				return;
			}

			if (cur[0] * 2 < 100001 && !visited[cur[0] * 2]) {
				queue.offer(new int[] { cur[0] * 2, cur[1] + 1 });
				visited[cur[0] * 2] = true;
			}
			if (cur[0] + 1 < 100001 && !visited[cur[0] + 1]) {
				queue.offer(new int[] { cur[0] + 1, cur[1] + 1 });
				visited[cur[0] + 1] = true;
			}
			if (cur[0] - 1 >= 0 && !visited[cur[0] - 1]) {
				queue.offer(new int[] { cur[0] - 1, cur[1] + 1 });
				visited[cur[0] - 1] = true;
			}
		}
	}
}