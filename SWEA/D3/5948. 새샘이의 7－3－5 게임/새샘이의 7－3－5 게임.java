import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution
{
	static int[] arr;
	static boolean[] visited;
	static List<Integer> answer;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String line = br.readLine();
			arr = new int[7];
			visited = new boolean[7];
			answer = new ArrayList<>();
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(line.split(" ")[i]);
			}
			
			combi(0, 0);
			answer = answer.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
			
			System.out.println("#" + test_case + " " + answer.get(4));
		}
	}
	public static void combi(int depth, int sum) {
		if (depth == 3) {
			answer.add(sum);
			return;
		}
		
		for (int i = depth; i < 7; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combi(depth+1, sum);
				combi(depth+1, sum + arr[i]);
				visited[i] = false;
			}
		}
	}

}