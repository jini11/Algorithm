import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int N, pin, minPin, minMove;
	static final int H = 5;
	static final int W = 9;
	static char[][] map;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < N; t++) {
			map = new char[H][W];
			pin = 0;
			
			for (int i = 0; i < H; i++) {
				String input = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = input.charAt(j);
					if (map[i][j] == 'o') {
						pin++;
					}
				}
			}
			
			minPin = pin;
			minMove = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] == 'o') {
						backtracking(i, j, pin, 0);
					}
				}
			}
			br.readLine();
			
			sb.append(minPin + " " + minMove + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void backtracking(int r, int c, int pin, int move) {
		if (pin <= minPin) {
			minPin = pin;
			minMove = move;
		}
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (!isBlock(nr, nc) && map[nr][nc] == 'o') {
				int nnr = nr + dr[i];
				int nnc = nc + dc[i];
				if (!isBlock(nnr, nnc) && map[nnr][nnc] == '.') {
					map[r][c] = '.';
					map[nr][nc] = '.';
					map[nnr][nnc] = 'o';
					
					for (int j = 0; j < H; j++) {
						for (int k = 0; k < W; k++) {
							if (map[j][k] == 'o') {
								backtracking(j, k, pin - 1, move + 1);
							}
						}
					}
					
					map[r][c] = 'o';
					map[nr][nc] = 'o';
					map[nnr][nnc] = '.';
 				}
			}
		}
	}
	
	private static boolean isBlock(int nr, int nc) {
		if (nr < 0 || nc < 0 || nr >= H || nc >= W) 
			return true;
		return false;
	}
	
}