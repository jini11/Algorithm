import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String input = br.readLine();
			int n = Integer.parseInt(input.split(" ")[0]);
			int k = Integer.parseInt(input.split(" ")[1]);
			List<Integer> list = new ArrayList<>();
			input = br.readLine();
			for (int i = 0; i < n; i++)
				list.add(Integer.parseInt(input.split(" ")[i]));
			Collections.sort(list, Collections.reverseOrder());
			int sum = 0;
			for (int i = 0; i < k; i++) {
				sum += list.get(i);
			}
			
			System.out.println("#" + test_case + " " + sum);
		}
	}

}