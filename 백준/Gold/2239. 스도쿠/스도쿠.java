import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static final int SIZE = 9;
	static int[][] map = new int[SIZE][SIZE];
	static ArrayList<int[]> zero = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < SIZE; i++) {
			String line = br.readLine();
			for (int j = 0; j < SIZE; j++) {
				map[i][j] = line.charAt(j) - '0';
				if (map[i][j] == 0) {
					zero.add(new int[] { i, j });
				}
			}
		}

		dfs(0);

		System.out.println(sb.toString());
	}

	private static boolean dfs(int idx) {
		if (idx == zero.size()) {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			return true;
		}

		int[] cur = zero.get(idx);

		for (int i = 1; i <= SIZE; i++) {
			if (!canWrite(i, cur[0], cur[1]))
				continue;
			map[cur[0]][cur[1]] = i;
			if (dfs(idx + 1))
				return true;
			map[cur[0]][cur[1]] = 0;
		}
		return false;
	}

	private static boolean canWrite(int num, int row, int col) {

		for (int i = 0; i < SIZE; i++) {
			if (map[row][i] == num) {
				return false;
			}
			if (map[i][col] == num) {
				return false;
			}
		}

		int nr = row / 3 * 3;
		int nc = col / 3 * 3;
		for (int i = nr, rowSize = nr + 3; i < rowSize; i++) {
			for (int j = nc, colSize = nc + 3; j < colSize; j++) {
				if (map[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}

}