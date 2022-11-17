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
			String input = br.readLine();
			String answer = "Exist";
			for (int i = 0; i < input.length()/2; i++) {
				if (input.charAt(i) == '*' || input.charAt(input.length()-i-1) == '*') break;
				if (input.charAt(i) != input.charAt(input.length()-i-1)) {
					answer = "Not exist";
					break;
				}
			}
			System.out.println("#" + test_case + " " + answer);
		}
	}

}