import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	static int N, L;
	static int[][] menu;
	static boolean[] visited;
	static int max;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String line = br.readLine();
			N = Integer.parseInt(line.split(" ")[0]);
			L = Integer.parseInt(line.split(" ")[1]);
			menu = new int[N][2];
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				line = br.readLine();
				for (int j = 0; j < 2; j++) {
					menu[i][j] = Integer.parseInt(line.split(" ")[j]);
				}
			}
			max = 0;
			combi(0, 0, 0);
			
			System.out.println("#" + test_case + " " + max);
		}
	}
	public static void combi(int depth, int flavor, int cal) {
		if (cal > L) {
			return;
		}
		if (cal <= L) {
			max = Math.max(max, flavor);
		}
		if (depth >= N) {
			return;
		}
		
		combi(depth+1, flavor + menu[depth][0], cal + menu[depth][1]);
		combi(depth+1, flavor, cal);
	}

}