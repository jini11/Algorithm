import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, minTime, time;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static Queue<int[]> queue;
	static List<int[]> virus;
	static int[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		visited = new boolean[N][N];
		queue = new ArrayDeque<int[]>();
		virus = new ArrayList<int[]>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new int[] { i, j });
				}
			}
		}

		minTime = Integer.MAX_VALUE;
		selected = new int[M];
		combi(0, 0);

		if (minTime == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minTime);
		}
	}

	private static void combi(int cnt, int start) {
		if (cnt == M) {
			// 바이러스 퍼트리기
			visited = new boolean[N][N];
			for (int i = 0; i < M; i++) {
				queue.add(new int[] { virus.get(selected[i])[0], virus.get(selected[i])[1], 0 });
				visited[virus.get(selected[i])[0]][virus.get(selected[i])[1]] = true;
			}
			time = 0;

			if (bfs()) {
				minTime = Math.min(minTime, time);
			}
			return;
		}

		for (int i = start; i < virus.size(); i++) {
			selected[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

	private static boolean bfs() {

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			if (map[cur[0]][cur[1]] != 2) {
				time = Math.max(time, cur[2]);
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] == 1)
					continue;
				visited[nr][nc] = true;
				queue.add(new int[] { nr, nc, cur[2] + 1 });
			}
		}

		// 모든 바이러스 다 퍼트렸는지 검사
		if (checkVirus()) {
			return true;
		}
		return false;
	}

	private static boolean checkVirus() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] != 1 && map[i][j] != 2) {
					return false;
				}
			}
		}
		return true;
	}
}