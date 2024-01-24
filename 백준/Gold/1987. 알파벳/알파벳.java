import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, res;
	static char[][] map;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] alpha = new int[27];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		res = 1;
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		dfs(0, 0, 0);
		
		System.out.println(res);
	}
	
	private static void dfs(int row, int col, int cnt) {
		if (alpha[map[row][col] - 'A'] == 1) {
			res = Math.max(res, cnt);
			return;
		}
		
		alpha[map[row][col] - 'A'] = 1;
		
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			dfs(nr, nc, cnt + 1);
		}
		alpha[map[row][col] - 'A'] = 0;
	}
}