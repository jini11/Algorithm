
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	static int cnt = 0;
	static int[] arr;
	static int N, K;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String line = br.readLine();
			N = Integer.parseInt(line.split(" ")[0]);
			K = Integer.parseInt(line.split(" ")[1]);
			arr = new int[N];
			line = br.readLine();
			for (int i=0 ;i<arr.length; i++)
				arr[i] = Integer.parseInt(line.split(" ")[i]);
			cnt = 0;
			dfs(0, 0);
			
			System.out.println("#" + test_case + " " + cnt);
		}
	}
	public static void dfs(int depth, int sum) {
		if (sum == K) {
			cnt++;
			return;
		}
		
		if (sum > K || depth >= N) 
			return;
		
		dfs(depth+1, sum + arr[depth]);
		dfs(depth+1, sum);
		
	}
}