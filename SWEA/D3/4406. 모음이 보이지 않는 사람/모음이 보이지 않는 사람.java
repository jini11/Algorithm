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
			input = input.replaceAll("[a,e,i,o,u]", "");
			
			System.out.println("#" + test_case + " " + input);
		}
	}

}