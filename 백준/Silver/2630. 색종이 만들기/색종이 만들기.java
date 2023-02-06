import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int blue = 0;
	static int white = 0;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recur(0, 0, n);
		
		System.out.printf("%d%n%d", white, blue);
	}

	public static void recur(int row, int col, int size) {
		
		if (checkPaper(row, col, size)) {
			if (map[row][col] == 1) {
				blue++;
			} else {
				white++;
			}
			return;
		}
		
		size /= 2;
		
		recur(row, col, size);
		recur(row + size, col, size);
		recur(row, col + size, size);
		recur(row + size, col + size, size);
	}

	public static boolean checkPaper(int row, int col, int size) {
		int color = map[row][col];
		
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (map[i][j] != color) {
					return false;
				}
			}
		}
		return true;
	}
}