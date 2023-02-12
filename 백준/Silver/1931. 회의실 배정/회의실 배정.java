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
		int N = Integer.parseInt(br.readLine());
		int[][] meet = new int[N][2];
		int result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			meet[i][0] = Integer.parseInt(st.nextToken());
			meet[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(meet, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}
		});

		int time = 0;
		for (int i = 0; i < N; i++) {
			if (time <= meet[i][0]) {
				time = meet[i][1];
				result++;
			}
		}
		System.out.println(result);
	}
}