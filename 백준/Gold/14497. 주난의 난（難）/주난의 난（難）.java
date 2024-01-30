import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;	

public class Main {

	static int N, M, res;
	static int[][] map;
	static int startRow, startCol, endRow, endCol;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		startRow = Integer.parseInt(st.nextToken()) - 1;
		startCol = Integer.parseInt(st.nextToken()) - 1;
		endRow = Integer.parseInt(st.nextToken()) - 1;
		endCol = Integer.parseInt(st.nextToken()) - 1;

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				char input = line.charAt(j);
				if (input == '1') {
					map[i][j] = 1;
				} else {
					map[i][j] = 0;
				}
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Deque<int[]> deque = new ArrayDeque<int[]>();
		int[][] countBoard = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(countBoard[i], -1);
		}
		countBoard[startRow][startCol] = 0;
		deque.add(new int[] { startRow, startCol });

		while (!deque.isEmpty()) {
			int[] cur = deque.poll();
			int cnt = countBoard[cur[0]][cur[1]];

			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (countBoard[nr][nc] != -1)
					continue;
				if (map[nr][nc] == 1) {
					countBoard[nr][nc] = cnt + 1;
					deque.add(new int[] { nr, nc });
				} else if (map[nr][nc] == 0) {
					countBoard[nr][nc] = cnt;
					deque.addFirst(new int[] { nr, nc });
				}
			}
		}

		res = countBoard[endRow][endCol] + 1;
		return res;
	}
}