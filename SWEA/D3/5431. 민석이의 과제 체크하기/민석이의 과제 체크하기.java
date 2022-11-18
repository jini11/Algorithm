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
			String line = br.readLine();
			int N = Integer.parseInt(line.split(" ")[0]);
			int K = Integer.parseInt(line.split(" ")[1]);
			int[] arr = new int[K];
			boolean[] answer = new boolean[N+1];
			line = br.readLine();
			for (int i=0; i<K; i++)
				arr[i] = Integer.parseInt(line.split(" ")[i]);
			
			for (int i=0; i< K; i++) {
				answer[arr[i]] = true;
			}
			System.out.print("#" + test_case + " ");
			for (int i=1; i<=N; i++) {
				if (!answer[i]) {
					System.out.print(i + " ");
				}
			}
			System.out.println();
		}
	}
	

}