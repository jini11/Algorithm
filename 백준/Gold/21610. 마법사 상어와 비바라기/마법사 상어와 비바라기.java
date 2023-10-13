import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static List<int[]> clouds;
	static int[][] action;
	static int sum, N, M;
	static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		action = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			action[i][0] = Integer.parseInt(st.nextToken()) - 1;
			action[i][1] = Integer.parseInt(st.nextToken());
		}

		// 구름 초기 세팅
		clouds = new ArrayList<int[]>();
		clouds.add(new int[] { N - 1, 0 });
		clouds.add(new int[] { N - 1, 1 });
		clouds.add(new int[] { N - 2, 0 });
		clouds.add(new int[] { N - 2, 1 });

		for (int i = 0; i < M; i++) {
			// 구름 이동
			moveCloud(action[i][0], action[i][1]);

			// 물 + 1
			rain();

			// 물복사버그
			copyRain();
			
			// 구름 생성
			makeCloud();
		}
		calcWater();

		System.out.println(sum);
	}

	private static void moveCloud(int d, int s) {
		List<int[]> movedCloud = new ArrayList<int[]>();

		for (int i = 0; i < clouds.size(); i++) {
			int[] cur = clouds.get(i);
			int nr = Math.abs((cur[0] + (dr[d] * (s % N)) + N) % N);
			int nc = Math.abs((cur[1] + (dc[d] * (s % N)) + N) % N);

			movedCloud.add(new int[] { nr, nc });
		}
		clouds.clear();
		for (int i = 0; i < movedCloud.size(); i++) {
			clouds.add(movedCloud.get(i));
		}
	}

	private static void rain() {
		for (int i = 0; i < clouds.size(); i++) {
			int[] cur = clouds.get(i);
			map[cur[0]][cur[1]] += 1;
		}
	}

	private static void copyRain() {
		for (int i = 0; i < clouds.size(); i++) {
			int[] cur = clouds.get(i);
			int cnt = 0;
			for (int j = 0; j < 4; j++) {
				int dir = (2 * j) + 1;
				int nr = cur[0] + dr[dir];
				int nc = cur[1] + dc[dir];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;

				if (map[nr][nc] != 0) {
					cnt++;
				}
			}
			map[cur[0]][cur[1]] += cnt;
		}
	}

	private static void makeCloud() {
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < clouds.size(); i++) {
			int[] cur = clouds.get(i);
			visited[cur[0]][cur[1]] = true;
		}
		clouds.clear();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 2 && !visited[i][j]) {
					clouds.add(new int[] { i, j });
					map[i][j] -= 2;
				}
			}
		}
	}

	private static void calcWater() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}