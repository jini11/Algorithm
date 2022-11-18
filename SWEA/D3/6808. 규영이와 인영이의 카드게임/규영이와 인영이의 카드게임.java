import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	static int[] arr;
	static int[] player;
	static boolean[] visited;
	static int guyoung;
	static int inyoung;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String line = br.readLine();
			arr = new int[9];
			player = new int[9];
			visited = new boolean[9];
			boolean[] chk = new boolean[19];
			for (int i = 0; i < 9; i++) {
				arr[i] = Integer.parseInt(line.split(" ")[i]);
				chk[arr[i]] = true;
			}
			int idx = 0;
			for (int i = 1; i < chk.length; i++) {
				if (!chk[i]) {
					player[idx] = i;
					idx++;
				}
			}
			int[] empty = new int[9];
			guyoung = 0;
			inyoung = 0;
			combi(0, empty);
			
			System.out.println("#" + test_case + " " + guyoung + " " +  inyoung);
		}
		
	}
	
	public static void combi(int depth, int[] arr) {
		if (depth == 9) {
			chkWinner(arr);
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arr[depth] = player[i];
				combi(depth+1, arr);
				visited[i] = false;
			}
		}
	}
	
	public static void chkWinner(int[] arr2) {
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 0; i < 9; i++) {
			if (arr[i] > arr2[i]) {
				sum1 += arr[i] + arr2[i];
			} else if (arr[i] < arr2[i]) {
				sum2 += arr[i] + arr2[i];
			}
		}
		
		if (sum1 > sum2) {
			guyoung++;
		} else if (sum1 < sum2) {
			inyoung++;
		}
	}
	

}