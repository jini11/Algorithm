import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] rank = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				rank[i][0] = Integer.parseInt(st.nextToken());
				rank[i][1] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(rank, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});
			
			int score = rank[0][1];
			int cnt = 1;
			for (int i = 1; i < N; i++) {
				if (score > rank[i][1]) {
					score = rank[i][1];
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}
}