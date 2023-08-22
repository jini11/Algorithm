import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] map;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		sb = new StringBuilder();
		recur(0, 0, N);

		System.out.println(sb.toString());
	}

	private static void recur(int row, int col, int range) {
		if (checkArea(row, col, range)) {
			sb.append(map[row][col]);
			return;
		}

		sb.append("(");

		int half = range / 2;
		recur(row, col, half);
		recur(row, col + half, half);
		recur(row + half, col, half);
		recur(row + half, col + half, half);

		sb.append(")");
	}

	private static boolean checkArea(int row, int col, int range) {
		int check = map[row][col];
		for (int i = row; i < row + range; i++) {
			for (int j = col; j < col + range; j++) {
				if (map[i][j] != check) {
					return false;
				}
			}
		}
		return true;
	}
}

