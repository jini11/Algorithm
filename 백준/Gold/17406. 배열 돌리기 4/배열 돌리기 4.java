import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static boolean[] isSelected;
	static int[][] command; 
	static int[] commandIdx;

	static int res = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		command = new int[K][4];
		commandIdx = new int[K];
		isSelected = new boolean[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			command[i][0] = i; 
			command[i][1] = Integer.parseInt(st.nextToken());
			command[i][2] = Integer.parseInt(st.nextToken());
			command[i][3] = Integer.parseInt(st.nextToken());
		}

		permutation(0); 

		System.out.println(res);
	}

	private static void permutation(int cnt) {
		if (cnt == K) {
			int[][] copyMap = cloneMap();
			for (int i = 0; i < K; i++) {
				int idx = commandIdx[i];
				rotateMap(copyMap, command[idx][1] - command[idx][3], command[idx][2] - command[idx][3], command[idx][3]);
			}
			res = Math.min(res, getMin(copyMap));
			return;
		}

		for (int i = 0; i < K; i++) {
			if (isSelected[i]) continue;
			commandIdx[cnt] = i;
			isSelected[i] = true;
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}

	private static int[][] cloneMap() {	
		int[][] copy = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			copy[i] = Arrays.copyOf(map[i], M + 1);
		}
		return copy;
	}

	private static void rotateMap(int[][] copyMap, int row, int col, int size) {
		if (size == 0) {
			return;
		}

		int firstNum = copyMap[row][col];			
		int nextRow = row + 1, nextCol = col;
		int curRow = row, curCol = col;
		int cnt = 1;
		int dir = 0;
		while (nextRow != row || nextCol != col) {
			if (cnt == size * 2) {
				cnt = 0;
				dir = (dir + 1) % 4;
			}
			copyMap[curRow][curCol] = copyMap[nextRow][nextCol];

			curRow = nextRow;
			curCol = nextCol;
			nextRow += dr[dir];
			nextCol += dc[dir];

			cnt++;
		}
		copyMap[row][col + 1] = firstNum;							
		rotateMap(copyMap, row + 1, col + 1, size - 1);
	}

	private static int getMin(int[][] copyMap) {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= M; j++) {
				sum += copyMap[i][j];
			}
            min = Math.min(min, sum);
		}
		return min;
	}
}