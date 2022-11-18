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
			int D = Integer.parseInt(line.split(" ")[0]);
			int H = Integer.parseInt(line.split(" ")[1]);
			int M = Integer.parseInt(line.split(" ")[2]);
			int answer = 0;
			if ((D <= 11 && H < 11) || (D <= 11 && H < 11 && M < 11)) {
				answer = -1;
			} else {
				if (M < 11) {
					H--;
					M = M + 60 - 11;
				} else M -= 11;
				if (H < 11) {
					D--;
					H = H + 24 - 11;
				} else H -= 11;
				D -= 11;
				answer = M + (H * 60) + (D * 24 * 60);
			}
			System.out.println("#" + test_case + " " + answer);
		}
		
	}

}