import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		if (np(input)) {
			for (int i = 0; i < N; i++) {
				System.out.print(input[i] + " ");
			}
		} else {
			System.out.println(-1);
		}
	}

	private static boolean np(int[] input) {
		int n = input.length - 1;

		int i = n;
		while (i > 0 && input[i] > input[i - 1]) {
			i--;
		}
		if (i == 0) {
			return false;
		}

		int j = n;
		while (input[i - 1] < input[j]) {
			j--;
		}
		swap(input, i - 1, j);

		int k = n;
		while (i < k) {
			swap(input, i++, k--);
		}
		return true;
	}

	private static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
}