import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int N = 4;
	static int[][] map = new int[N][N];
	static State shark;
	static State[] fishes = new State[17];
	static int res;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static class State {
		int row, col, dir;
		boolean alive;

		public State(int row, int col, int dir, boolean alive) {
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.alive = alive;
		}

		public State clone() {
			return new State(row, col, dir, alive);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				map[i][j] = num;
				fishes[num] = new State(i, j, dir, true);
			}
		}

		int eaten = map[0][0];
		fishes[map[0][0]].alive = false;
		shark = new State(0, 0, fishes[map[0][0]].dir, true);
		map[0][0] = 0;

		start(eaten);

		System.out.println(res);
	}

	private static void start(int sum) {
		res = Math.max(res, sum);

		// 맵 복사
		int[][] copyMap = copyMap(map);

		// 물고기 상태 복사
		State[] copyFish = copyFish(fishes);

		// 물고기 이동
		moveFish();

		// 상어 이동 & 재귀
		for (int i = 1; i < 4; i++) {
			int nr = shark.row + (i * dr[shark.dir]);
			int nc = shark.col + (i * dc[shark.dir]);

			// 경계 밖이면 갈 수 break;
			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				break;

			// 물고기가 없는 칸이면 continue;
			if (map[nr][nc] == 0)
				continue;

			// 물고기가 있으면, 먹고 방향 갱신 -> 재귀
			if (map[nr][nc] != 0) {
				int eaten = map[nr][nc];
				map[nr][nc] = 0;
				fishes[eaten].alive = false;
				State beforeShsark = shark.clone();
				shark = new State(fishes[eaten].row, fishes[eaten].col, fishes[eaten].dir, true);
				start(sum + eaten);
				fishes[eaten].alive = true;
				map[nr][nc] = eaten;
				shark = beforeShsark.clone();
			}

		}
		// 맵 복구
		map = copyMap(copyMap);

		// 물고기 상태 복구
		fishes = copyFish(copyFish);

	}

	private static void moveFish() {
		for (int i = 1; i < 17; i++) {
			if (!fishes[i].alive)
				continue;
			int d = fishes[i].dir;
			for (int j = 0; j < 8; j++) {
				// 다음 갈 칸
				int dir = (d + j) % 8;
				int nr = fishes[i].row + dr[dir];
				int nc = fishes[i].col + dc[dir];
				fishes[i].dir = dir;

				// 갈 수 없으면 계속(상어가 있거나, 경계 넘을 때)
				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;

				if (shark.row == nr && shark.col == nc)
					continue;

				// 갈 수 있으면 가기(0일 때, 다른 물고기가 있을 때)
				if (map[nr][nc] == 0) {
					map[fishes[i].row][fishes[i].col] = 0;
					map[nr][nc] = i;
					fishes[i].row = nr;
					fishes[i].col = nc;
					break;
				} else {
					// map 에서의 위치 교체
					int temp = map[fishes[i].row][fishes[i].col];
					map[fishes[i].row][fishes[i].col] = map[nr][nc];
					map[nr][nc] = temp;

					// state 상태 교체(row, col만 교체)
					int tempRow = fishes[map[fishes[i].row][fishes[i].col]].row;
					int tempCol = fishes[map[fishes[i].row][fishes[i].col]].col;
					fishes[map[fishes[i].row][fishes[i].col]].row = fishes[map[nr][nc]].row;
					fishes[map[fishes[i].row][fishes[i].col]].col = fishes[map[nr][nc]].col;
					fishes[map[nr][nc]].row = tempRow;
					fishes[map[nr][nc]].col = tempCol;

					break;
				}

			}
		}
	}

	private static int[][] copyMap(int[][] map) {
		int[][] copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		return copyMap;
	}

	private static State[] copyFish(State[] fishes) {
		State[] copyFishes = new State[17];
		for (int i = 1; i < 17; i++) {
			copyFishes[i] = fishes[i].clone();
		}
		return copyFishes;
	}
}
