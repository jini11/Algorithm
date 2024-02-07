import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, N;
	static char[][] map;
	static List<int[]> bombList;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		bombList = new ArrayList<int[]>();
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'O') {
					bombList.add(new int[] {i, j});
				}
			}
		}
		
		for (int i = 1; i < N; i++) {
			
			if (i % 2 != 0) { // 채우고
				addBomb();
			} else { // 터지고
				fireBomb();
			}
			
		}
		printMap();
	}
	
	private static void printMap() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	private static void addBomb() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = 'O';
			}
		}
	}
	
	private static void fireBomb() {
		for (int[] bomb : bombList) {
			map[bomb[0]][bomb[1]] = '.';
			for (int i = 0; i < 4; i++) {
				int nr = bomb[0] + dr[i];
				int nc = bomb[1] + dc[i];
				
				if (isOut(nr, nc)) continue;
				
				map[nr][nc] = '.';
			}
		}
		
		bombList = new ArrayList<int[]>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'O') {
					bombList.add(new int[] {i, j});
				}
			}
		}
	}
	
	private static boolean isOut(int nr, int nc) {
		if (nr < 0 || nr >= R || nc < 0 || nc >= C) 
			return true;
		return false;
	}
}