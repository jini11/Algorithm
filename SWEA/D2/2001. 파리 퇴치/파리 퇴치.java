import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] dp = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= N; j++) {
					int number = Integer.parseInt(st.nextToken());
					dp[i][j] = number + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
				}
			}

			int max = 0;
			for (int i = M; i <= N; i++) {
				for (int j = M; j <= N; j++) {
					max = Math.max(max, dp[i][j] - dp[i - M][j] - dp[i][j - M] + dp[i - M][j - M]);
				}
			}
			sb.append("#" + (t+1) + " " + max + "\n");
		}
		System.out.println(sb.toString());
	}
}
