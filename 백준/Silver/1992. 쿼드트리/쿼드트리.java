import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] map;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(line.split("")[j]);
			}
		}
		recur(0, 0, n);
		System.out.println(sb.toString());
	}
	private static void recur(int row, int col, int size) {
		if (check(row, col, size)) {
			sb.append(map[row][col]);
			return;
		}
		
		size /= 2;
		sb.append("(");
		
		recur(row, col, size);
		recur(row, col + size, size);
		recur(row + size, col, size);
		recur(row + size, col + size, size);
		sb.append(")");
	}
	private static boolean check(int row, int col, int size) {
		int video = map[row][col];
		
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (map[i][j] != video) {
					return false;
				}
			}
		}
		return true;
	}
}