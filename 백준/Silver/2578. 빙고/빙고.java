import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[5][5];
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] nums = new int[25];
		int idx = 0;
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				nums[idx++] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 25; i++) {
			chkNum(nums[i]);
			if (chkBingo() >= 3) {
				System.out.println(i + 1);
				return;
			}

		}
	}

	public static void chkNum(int num) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (map[i][j] == num) {
					map[i][j] = 0;
					return;
				}
			}
		}
	}

	public static int chkBingo() {
		int bingo = 0;
		for (int i = 0; i < 5; i++) {
			int row = 0;
			int col = 0;
			for (int j = 0; j < 5; j++) {
				if (map[i][j] == 0)
					row++;
			}

			for (int j = 0; j < 5; j++) {
				if (map[j][i] == 0)
					col++;
			}

			if (row == 5)
				bingo++;
			if (col == 5)
				bingo++;
		}
		
		
		if (map[0][0] == 0 && map[1][1] == 0 && map[2][2] == 0 && map[3][3] == 0 && map[4][4] == 0) {
			bingo++;
		}
		if (map[0][4] == 0 && map[1][3] == 0 && map[2][2] == 0 && map[3][1] == 0 && map[4][0] == 0) {
			bingo++;
		}
		return bingo;
	}

}