import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, location, idx;
	static char[] arr, res;
	static boolean check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		while ((line = br.readLine()) != null)  {
			StringTokenizer st = new StringTokenizer(line, " ");
			String input = st.nextToken();
			location = Integer.parseInt(st.nextToken());
			arr = input.toCharArray();
			N = arr.length;
			res = new char[N];
			idx = 1;
			check = false;
			
			System.out.print(input + " " + location + " = ");
			per(0, 0);
			if (!check) {
				System.out.println("No permutation");
			}
		}
		
	}
	
	private static void per(int cnt, int flag) {
		if (cnt == N) {
			if (idx == location) {
				for (int i = 0; i < res.length; i++) {
					System.out.print(res[i]);
				}
				System.out.println();
				check = true;
			}
			idx++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) == 0) {
				res[cnt] = arr[i];
				per(cnt + 1, (flag | 1 << i));
			}
		}
		return;
	}
}