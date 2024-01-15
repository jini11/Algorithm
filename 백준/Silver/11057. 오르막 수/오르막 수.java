import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[10];
		Arrays.fill(dp, 1);
		
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < 10; j++) {
				int sum = 0;
				for (int k = j; k < 10; k++) {
					sum += (dp[k] % 10007);
				}
				dp[j] = sum;
			}
		}
		
		System.out.println(Arrays.stream(dp).sum() % 10007);
	}
}