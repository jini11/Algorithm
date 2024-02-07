import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static boolean[] number = new boolean[10];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		if (M > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				number[Integer.parseInt(st.nextToken())] = true;
			}	
		}
		
		int res = Math.abs(100 - N);
		for (int i = 0; i <= 999999; i++) {
			String num = String.valueOf(i);
			
			boolean flag = false;
			for (int j = 0; j < num.length(); j++) {
				if (number[num.charAt(j) - '0']) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				int min = Math.abs(N - i) + num.length();
				res = Math.min(min, res);
			}
		}
		System.out.println(res);
	}

}