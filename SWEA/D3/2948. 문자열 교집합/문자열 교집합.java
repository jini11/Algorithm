import java.util.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();
			Set<String> set = new HashSet<>();
			for (int i = 0; i < N; i++) {
				String word = sc.next();
				set.add(word);
			}
			int cnt = 0;
			for (int i = 0; i < M; i++) {
				String word = sc.next();
				if (set.contains(word)) cnt++;
			}
			
			System.out.println("#" + test_case + " " + cnt);
		}
	}

}