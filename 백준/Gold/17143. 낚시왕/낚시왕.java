import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Shark {
		int row, col, speed, dir, size;
		boolean isCatch;

		public Shark(int row, int col, int speed, int dir, int size, boolean isCatch) {
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
			this.isCatch = isCatch;
		}
	}

	static int R, C, M;
	static int[][] map;
	static Shark[] sharks;
	static int human;
	static int res;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sharks = new Shark[M + 1];
		map = new int[R][C];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			sharks[i] = new Shark(r, c, s, d, z, false);
			map[r][c] = i;
		}

		human = 0; // 열
		while (human < C) {
			catchFish();
			moveShark();
			if (setShark() == 0)
				break;
			human++;
		}

		System.out.println(res);

	}

	private static void catchFish() {
		for (int row = 0; row < R; row++) {
			if (map[row][human] != 0) {
				int idx = map[row][human];
				res += sharks[idx].size;
				sharks[idx].isCatch = true;
				map[row][human] = 0;
				return;
			}
		}
	}

	private static void moveShark() {
		for (int i = 1; i <= M; i++) {
			if (!sharks[i].isCatch) {
				map[sharks[i].row][sharks[i].col] = 0;
				int d = sharks[i].dir;
				int s = sharks[i].speed;
				if (d == 1 || d == 2) {
					sharks[i].speed = s % ((R - 1) * 2);
				} else if (d == 3 || d == 4) {
					sharks[i].speed = s % ((C - 1) * 2);
				}
				move(sharks[i].row, sharks[i].col, i, 0);
			}
		}
	}

	private static void move(int row, int col, int idx, int cnt) {
		if (cnt == sharks[idx].speed) {
			sharks[idx].row = row;
			sharks[idx].col = col;
			return;
		}

		int d = sharks[idx].dir;

		int nr = row + dr[d - 1];
		int nc = col + dc[d - 1];
		if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
			if (d == 1) {
				d = 2;
			} else if (d == 2) {
				d = 1;
			} else if (d == 3) {
				d = 4;
			} else if (d == 4) {
				d = 3;
			}
			sharks[idx].dir = d;
			nr = row + dr[d - 1];
			nc = col + dc[d - 1];
		}
		move(nr, nc, idx, cnt + 1);
	}

	private static int setShark() {
		int cnt = 0;
		for (int i = 1; i <= M; i++) {
			if (!sharks[i].isCatch) {
				int idx = map[sharks[i].row][sharks[i].col];
				if (idx != 0) {
					if (sharks[idx].size < sharks[i].size) {
						map[sharks[i].row][sharks[i].col] = i;
						sharks[idx].isCatch = true;
					} else {
						sharks[i].isCatch = true;
					}
				} else {
					map[sharks[i].row][sharks[i].col] = i;
					cnt++;
				}				
			}
		}
		return cnt;
	}
}