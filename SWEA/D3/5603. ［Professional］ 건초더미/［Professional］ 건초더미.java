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
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			int sum = 0;
			for (int i=0 ; i<N; i++) {
				arr[i] = Integer.parseInt(br.readLine());
				sum += arr[i];
			}
			sum = sum / N;
			int cnt = 0;
			for (int i=0; i<arr.length; i++) {
				cnt += Math.abs(sum - arr[i]);				
			}
			System.out.println("#" + test_case + " " + cnt/2);
		}
	}

}