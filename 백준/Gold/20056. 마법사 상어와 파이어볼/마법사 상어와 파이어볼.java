import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Node>[][] map;
	static int N, M, K, sum;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static class Node {
		int r, c, m, s, d;

		public Node(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Node(r, c, m, s, d));
		}

		for (int i = 0; i < K; i++) {
			// 모든 파이어볼 이동
			moveFireball();

			// 한 칸에 2개 이상 있는 경우 퍼지기
			spreadFireball();

			// 질량이 0인 파이어볼 소멸
		}

		calcSum();

		System.out.println(sum);
	}

	private static void moveFireball() {
		List<Node>[][] moved = makeMap();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (Node cur : map[i][j]) {
					int nr = (cur.r + dr[cur.d] * (cur.s % N) + N) % N;
					int nc = (cur.c + dc[cur.d] * (cur.s % N) + N) % N;
					moved[nr][nc].add(new Node(nr, nc, cur.m, cur.s, cur.d));
				}
			}
		}

		// moved -> map 에 옮기기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j].clear();
				for (Node node : moved[i][j]) {
					map[i][j].add(node);
				}
			}
		}
	}

	private static void spreadFireball() {
		List<Node>[][] moved = makeMap();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int size = map[i][j].size();
				if (size < 2)
					continue;
				int nm = 0;
				int ns = 0;
				int nd = 0;
				boolean flag = true;
				int dir = map[i][j].get(0).d % 2;
				for (Node node : map[i][j]) {
					nm += node.m;
					ns += node.s;
					if (node.d % 2 != dir) {
						flag = false;
					}
				}
				nm /= 5;
				ns /= size;
				if (flag) {
					nd = 0; // 0246
				} else {
					nd = 1; // 1357
				}

				map[i][j].clear();
				if (nm == 0)
					continue;

				for (int k = 0; k < 4; k++) {
					map[i][j].add(new Node(i, j, nm, ns, nd));
					nd += 2;
				}
			}
		}
	}

	private static void calcSum() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() == 0)
					continue;
				for (Node node : map[i][j]) {
					sum += node.m;
				}
			}
		}
	}

	private static List<Node>[][] makeMap() {
		List<Node>[][] moved = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				moved[i][j] = new ArrayList<>();
			}
		}
		return moved;
	}
}