import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] map;
	static List<int[]> queenList, knightList;
	static int[] dr = {1, 0, -1, 0, -1, -1, 1, 1};
	static int[] dc = {0, 1, 0, -1, 1, -1, 1, -1};
	static int[] kdr = {-2, -1, 1, 2, -2, -1, 1, 2};
	static int[] kdc = {1, 2, 2, 1, -1, -2, -2, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		queenList = new ArrayList<>();
		knightList = new ArrayList<>();
			
		st = new StringTokenizer(br.readLine());
		int queen = Integer.parseInt(st.nextToken());
		for (int j = 0; j < queen; j++) {
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			map[row][col] = 2;
			queenList.add(new int[] {row, col});
		}
		
		st = new StringTokenizer(br.readLine());
		int knight = Integer.parseInt(st.nextToken());
		for (int j = 0; j < knight; j++) {
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			map[row][col] = 1;
			knightList.add(new int[] {row, col});
		}
		
		st = new StringTokenizer(br.readLine());
		int pawn = Integer.parseInt(st.nextToken());
		for (int j = 0; j < pawn; j++) {
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			map[row][col] = 3;
		}
		
		move_queen();
		move_knight();
		
		System.out.println(calcSafe());
	}
	
	private static void move_knight() {
		for (int[] knight : knightList) {
			for (int i = 0; i < 8; i++) {
				int nr = knight[0] + kdr[i];
				int nc = knight[1] + kdc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if (map[nr][nc] > 0) continue;
				map[nr][nc] = -1;
			}
		}
	}
	
	private static void move_queen() {
		for (int[] queen : queenList) {
			for (int i = 0; i < 8; i++) {
				int nr = queen[0];
				int nc = queen[1];
				while (true) {
					nr += dr[i];
					nc += dc[i];
					
					if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 3) break;
					if (map[nr][nc] > 0) break;
					
					map[nr][nc] = -1;
				}
			}
		}
	}
	
	private static int calcSafe() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}