import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution{
	static int N, W, H;
	static int[][] map;
	static int[][] copy;
	static int res;
	static int[] ball;
	static boolean isContinue;
	
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][] visited;
	static Queue<int[]> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			res = W * H;

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ball = new int[N];
			isContinue = true;
			permutation(0);

			sb.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void permutation(int cnt) {
		if (cnt == N) {
			copy = copyMap(map);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < H; j++) {
					if (copy[j][ball[i]] != 0) {
						visited = new boolean[H][W];
						breakBrick(j, ball[i]);
						downBrick();
						break;
					}
				}
			}
			res = Math.min(res, countBrick());
			if (res == 0) isContinue = false;
			return;
		}

		if (!isContinue) return;
		
		for (int i = 0; i < W; i++) {
			ball[cnt] = i;
			permutation(cnt + 1);
		}
	}

	private static int[][] copyMap(int[][] map) {
		int[][] copy = new int[H][W];
		for (int i = 0; i < H; i++) {
			copy[i] = map[i].clone();
		}
		return copy;
	}

	private static void breakBrick(int row, int col) {
		queue = new ArrayDeque<>();
		queue.add(new int[] { row, col, copy[row][col] });
		visited[row][col] = true;
		copy[row][col] = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (cur[2] > 1) {
				for (int i = 0; i < 4; i++) {
					dfs(cur[0], cur[1], i, cur[2] - 1);
				}
			} else {
				copy[cur[0]][cur[1]] = 0;
			}
		}
	}

	private static void dfs(int row, int col, int dir, int cnt) {
		if (cnt == 0) {
			return;
		}

		int nr = row + dr[dir];
		int nc = col + dc[dir];

		if (nr < 0 || nr >= H || nc < 0 || nc >= W)
			return;

		if (copy[nr][nc] > 0) {

			queue.add(new int[] { nr, nc, copy[nr][nc] });

			copy[nr][nc] = 0;
			visited[nr][nc] = true;
		}
		dfs(nr, nc, dir, cnt - 1);
	}

	private static void downBrick() {
		int[][] temp = new int[H][W];

		for (int i = 0; i < W; i++) {
			Queue<Integer> queue = new ArrayDeque<Integer>();
			for (int j = 0; j < H; j++) {
				if (copy[j][i] != 0) {
					queue.add(copy[j][i]);
				}
			}

			for (int j = H - queue.size(); j < H; j++) {
				temp[j][i] = queue.poll();
			}
		}

		copy = copyMap(temp);
	}

	private static int countBrick() {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (copy[i][j] != 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}