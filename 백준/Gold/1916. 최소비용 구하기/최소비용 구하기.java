import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[][] adjMatrix = new int[N+1][N+1];
		int[] distance = new int[N+1];
		boolean[] visited = new boolean[N+1];

		Arrays.fill(distance, INF);
		for (int i = 1; i <= N; i++) {
			Arrays.fill(adjMatrix[i], INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjMatrix[from][to] = Math.min(adjMatrix[from][to], weight);
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		distance[start] = 0;

		for (int i = 1; i <= N; i++) {
			int current = -1;
			int min = INF;

			for (int j = 1; j <= N; j++) {
				if (!visited[j] && min > distance[j]) {
					min = distance[j];
					current = j;
				}
			}

			if (current == -1) break;
			visited[current] = true;
			if (current == end) break;

			for (int j = 1; j <= N; j++) {
				if (!visited[j] && adjMatrix[current][j] != INF && distance[j] > min + adjMatrix[current][j]) {
					distance[j] = min + adjMatrix[current][j];
				}
			}
		}

		if (distance[end] != INF) {
			System.out.println(distance[end]);
		} else {
			System.out.println(-1);
		}
	}
}