import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	static int[][] map;
	static boolean[] flag;
	static int answer;
	static int N;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			flag = new boolean[N];
			answer = 0;
			
			dfs(0);
			
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	public static void dfs(int depth) {
		if (depth == N) {
			answer++;
			return;
		}
		
		for (int i=0; i<N; i++) {
			if (!flag[i] && checkLine(depth, i)) {
				map[depth][i] = 1;
				flag[i] = true;
				dfs(depth+1);
				map[depth][i] = 0;
				flag[i] = false;
			}
		}
	}
	
	public static boolean checkLine(int r, int c) {
		
		for (int i=1; i<=r; i++) { // 왼쪽 위 대각선
			int nr = r - i;
			int nc = c - i;
			if (nr >= 0 && nc >= 0 && map[nr][nc] == 1) {
				return false;
			}
		}
		
		for (int i=1; i<=r; i++) { // 오른쪽 위 대각선
			int nr = r - i;
			int nc = c + i;
			if (nr >= 0 && nc < N && map[nr][nc] == 1) {
				return false;
			}
		}
		return true;
	}
}