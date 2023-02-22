import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Solution {
	static char[][] map;
	static int[] train = new int[2];
	static char trainHead;
	static int[] dx = { 1, -1 };
	static int h, w;
	static List<Character> trainShape = Arrays.asList('^', 'v', '<', '>');

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String line = br.readLine();
			h = Integer.parseInt(line.split(" ")[0]);
			w = Integer.parseInt(line.split(" ")[1]);
			map = new char[h][w];
			for (int i = 0; i < h; i++) {
				line = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);
					if (trainShape.contains(map[i][j])) {
						trainHead = map[i][j];
						train[0] = i;
						train[1] = j;
					}
				}
			}
			int N = Integer.parseInt(br.readLine());
			String way = br.readLine();

			for (int i = 0; i < way.length(); i++) {
				switch (way.charAt(i)) {
				case 'U':
					move(0);
					break;
				case 'D':
					move(1);
					break;
				case 'L':
					move(2);
					break;
				case 'R':
					move(3);
					break;
				case 'S':
					int dir = trainShape.indexOf(trainHead);
					shoot(dir);
					break;
				}
			}

			System.out.print("#" + t + " ");
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}

	private static void move(int dir) {
		trainHead = trainShape.get(dir);
		int nr = train[0] + dr[dir];
		int nc = train[1] + dc[dir];

		if (nr < 0 || nr >= h || nc < 0 || nc >= w || map[nr][nc] == '*' || map[nr][nc] == '#' || map[nr][nc] == '-') {
			map[train[0]][train[1]] = trainHead;
			return;
		}
		map[train[0]][train[1]] = '.';
		map[nr][nc] = trainHead;
		train[0] = nr;
		train[1] = nc;
	}

	private static void shoot(int dir) {
		int row = train[0];
		int col = train[1];

		while (true) {
			int nr = row + dr[dir];
			int nc = col + dc[dir];

			if (nr < 0 || nr >= h || nc < 0 || nc >= w || map[nr][nc] == '#')
				break;
			if (map[nr][nc] == '*') {
				map[nr][nc] = '.';
				break;
			}

			row = nr;
			col = nc;
		}
	}

}