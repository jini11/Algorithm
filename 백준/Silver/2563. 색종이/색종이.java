import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int SIZE = 100;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		boolean[][] paper = new boolean[SIZE][SIZE];
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken());
			int bottom = Integer.parseInt(st.nextToken());

			for (int l = left, leftSize = left + 10; l < leftSize; l++) {
				for (int b = bottom, bottomSize = bottom + 10; b < bottomSize; b++) {
					paper[l][b] = true;
				}
			}
		}

		int total = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (paper[i][j]) {
					total++;
				}
			}
		}
		System.out.println(total);
	}
}