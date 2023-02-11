import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static String[][] map;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new String[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.split("")[j];
			}
		}

		recur(0, 0, N);
		
		System.out.println(sb);
	}

	private static void recur(int row, int col, int n) {
		if (checkMap(row, col, n)) {
			sb.append(map[row][col]);
			return;
		}

		n /= 2;
		sb.append("(");
		recur(row, col, n);
		recur(row, col + n, n);
		recur(row + n, col, n);
		recur(row + n, col + n, n);
		sb.append(")");

	}

	private static boolean checkMap(int row, int col, int n) {
		String one = map[row][col];
		for (int i = row; i < row + n; i++) {
			for (int j = col; j < col + n; j++) {
				if (!map[i][j].equals(one)) {
					return false;
				}
			}
		}
		return true;
	}
}