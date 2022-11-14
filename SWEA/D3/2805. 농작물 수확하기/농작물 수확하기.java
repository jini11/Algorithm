import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			
			int sum = 0;
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			
			for (int i = 0; i <= N/2; i++) {
				for (int j = N/2 - i; j <= N/2+i; j++) {
					sum += map[i][j];
				}
			}
			
			for (int i = N/2-1; i >= 0; i--) {
				for (int j = N/2-i; j <= N/2+i; j++) {
					sum += map[N-i-1][j];
				}
			}
			
			System.out.println("#" + test_case + " " + sum);
		}
	}
}