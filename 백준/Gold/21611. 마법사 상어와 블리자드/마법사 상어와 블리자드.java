import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M, d, s, startRow, startCol;
	static int[][] map;
	static int[] balls = new int[3];
	static Queue<Integer> queue;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };
	static int[] sdr = { -1, 1, 0, 0 };
	static int[] sdc = { 0, 0, -1, 1 };
	static int[][] actions;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		startRow = N / 2;
		startCol = N / 2;
		actions = new int[M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken()) - 1;
			s = Integer.parseInt(st.nextToken());
			actions[i][0] = d;
			actions[i][1] = s;
		}

		for (int i = 0; i < M; i++) {
			queue = new ArrayDeque<Integer>();

			// 구슬 파괴
			destroyBall(i);

			// 구슬 빈칸 이동
			moveBall();

			// 구슬 폭발
			bombBall();

			// 구슬 변화
			changeBall();

			// 맵에 반영
			changeMap();
		}

		System.out.println(balls[0] + (2 * balls[1]) + (3 * balls[2]));
	}

	private static void destroyBall(int idx) {
		int nr = startRow;
		int nc = startCol;
		for (int i = 0; i < actions[idx][1]; i++) {
			nr += sdr[actions[idx][0]];
			nc += sdc[actions[idx][0]];
			map[nr][nc] = 0;
		}
	}

	private static void moveBall() {
		int idx = 0;
		int cnt = 0; // 현재 이동 횟수
		int num = 1; // 몇 칸 이동
		int repeat = 0; // 방향 전환 횟수
		int nr = startRow;
		int nc = startCol;
		while (true) {
			nr = nr + dr[idx];
			nc = nc + dc[idx];
			if (map[nr][nc] != 0) {
				queue.add(map[nr][nc]);
			}
			map[nr][nc] = 0;
			cnt++;
			if (cnt == num) {
				cnt = 0;
				repeat++;
				idx = (idx + 1) % 4;
				if (repeat == 2) {
					repeat = 0;
					num += 1;
				}
			}
			if (nr == 0 && nc == 0)
				break;
		}
	}

	private static void bombBall() {
		int size = queue.size();
		while (true) {
			Queue<Integer> moved = new ArrayDeque<Integer>();
			List<Integer> list = new ArrayList<Integer>();
			while (!queue.isEmpty()) {
				int ball = queue.poll();
				if (list.size() != 0 && list.get(0) == ball) {
					list.add(ball);
				} else {
					if (list.size() < 4) {
						for (int i = 0; i < list.size(); i++) {
							moved.add(list.get(0));
						}
					} else {
						balls[list.get(0) - 1] += list.size();
					}

					list.clear();
					list.add(ball);
				}
			}
			if (list.size() < 4) {
				for (int i = 0; i < list.size(); i++) {
					moved.add(list.get(0));
				}
			} else {
				balls[list.get(0) - 1] += list.size();
			}
			
			while (!moved.isEmpty()) {
				queue.add(moved.poll());
			}
			if (size == queue.size())
				break;
			size = queue.size();
		}
		
	}

	private static void changeBall() {
		Queue<Integer> moved = new ArrayDeque<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			int ball = queue.poll();
			if (list.size() != 0 && list.get(0) == ball) {
				list.add(ball);
			} else {
				if (list.size() != 0) {
					moved.add(list.size()); // 개수
					moved.add(list.get(0)); // 번호
				}
				list.clear();
				list.add(ball);
			}
		}
		if (list.size() != 0) {
			moved.add(list.size()); // 개수
			moved.add(list.get(0)); // 번호
		}
		while (!moved.isEmpty()) {
			queue.add(moved.poll());
		}
	}

	private static void changeMap() {
		int idx = 0;
		int cnt = 0; // 현재 이동 횟수
		int num = 1; // 몇 칸 이동
		int repeat = 0; // 방향 전환 횟수
		int nr = startRow;
		int nc = startCol;

		map = new int[N][N];

		while (!queue.isEmpty()) {
			nr = nr + dr[idx];
			nc = nc + dc[idx];

			map[nr][nc] = queue.poll();
			cnt++;
			if (cnt == num) {
				cnt = 0;
				repeat++;
				idx = (idx + 1) % 4;
				if (repeat == 2) {
					repeat = 0;
					num += 1;
				}
			}
			if (nr == 0 && nc == 0)
				break;
		}
	}
}