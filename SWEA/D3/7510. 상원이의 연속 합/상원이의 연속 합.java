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
			int cnt = 0;
			int left = 1;
			int right = 1;
			int sum = 1;
			while(right <= N && left <= N) {
				if (sum == N) {
					cnt++;
				}
				if (sum <= N) {
					right++;
					sum += right;
				} else if (sum > N) {
					sum -= left;
					left++;
				}
			}
			
			System.out.println("#" + test_case + " " + cnt);
		}
		
	}

}