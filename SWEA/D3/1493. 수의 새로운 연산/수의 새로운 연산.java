import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int[][] map = new int[301][301];
		int number = 1;
		for (int i=0; i <= 300; i++) {
			for (int j=i; j>=0; j--) {
				map[i-j][j] = number++;
			}
		}
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String line = br.readLine();
			int p = Integer.parseInt(line.split(" ")[0]);
			int q = Integer.parseInt(line.split(" ")[1]);
			int[] x = new int[2];
			int[] y = new int[2];
			if (p > q) {
				int tmp = p;
				p = q;
				q = tmp;
			}
			
			for (int i=0; i<=300; i++) {
				for (int j=0; j<=300; j++) {
					if (map[i][j] == p) {
						x[0] = i;
						y[0] = j;
					}
					if (map[i][j] == q) {
						x[1] = i;
						y[1] = j;
						break;
					}
				}
			}
			int sumx = x[0] + x[1] + 1;
			int sumy = y[0] + y[1] + 1;
			
			System.out.println("#" + test_case + " " + map[sumx][sumy]);
		}
	}
}