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
			String line = br.readLine();
			int[] arr = new int[N];
			int max = -1;
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(line.split(" ")[i]);
			}
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					int num = arr[i] * arr[j];
					if(chkNumber(num)) {
						max = Math.max(max, num);
					}
				}
			}
			System.out.println("#" + test_case + " " + max);
		}
		
	}
	
	public static boolean chkNumber(int num) {
		if (num < 10) return true;
		String number = Integer.toString(num);
		for (int i = 0; i < number.length()-1; i++) {
			if (number.charAt(i) - '0' > number.charAt(i+1) - '0') {
				return false;
			}
		}
		return true;
	}

}