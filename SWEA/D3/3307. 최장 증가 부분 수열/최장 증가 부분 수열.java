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
			int[] arr = new int[N];
			int[] num = new int[N];
			String line = br.readLine();
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(line.split(" ")[i]);
			}
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				num[i] = 1;
                for (int j = 0; j < i; j++) {
                 	if (arr[i] > arr[j]) {
                     	num[i] = Math.max(num[i], num[j]+1);
                        if (num[max] < num[i]) max = i;
                    }
                }
			}
			System.out.println("#" + test_case + " " + num[max]);
		}
	}

}