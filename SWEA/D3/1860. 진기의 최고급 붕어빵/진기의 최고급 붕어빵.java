import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

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
			int M = Integer.parseInt(line.split(" ")[1]);
			int K = Integer.parseInt(line.split(" ")[2]);
			int[] come = new int[N];
			line = br.readLine();
			String answer = "Possible";
			int bread = 0;
			int max = 0;
			int idx = 0;
			for (int i=0; i<N; i++) {
				come[i] = Integer.parseInt(line.split(" ")[i]);
			}
			Arrays.sort(come);
			max = come[come.length-1];
			for (int i=0 ; i<=max; i++) {
				if (i % M == 0 && i != 0) {
					bread += K;
				}
				if (i == come[idx]) {
					if (bread <= 0) {
						answer = "Impossible";
						break;
					} 
                    bread--;
					idx++;
				}
                if (idx >= come.length) {
                    break;
                }
			}
			
			System.out.println("#" + test_case + " " + answer);
		}
	}
}