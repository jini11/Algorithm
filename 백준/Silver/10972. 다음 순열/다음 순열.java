import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		if (np(arr)) {
			for (int i = 0; i < N; i++) {
				System.out.print(arr[i] + " ");
			}
		} else {
			System.out.println(-1);
		}
	}
	private static boolean np(int[] arr) {
		int n = arr.length - 1;

		int i = n;
		while (i > 0 && arr[i - 1] >= arr[i]) {
			i--;
		}
		if (i == 0) {
			return false;
		}

		int j = n;
		while (arr[i - 1] >= arr[j]) {
			j--;
		}

		swap(arr, i - 1, j);

		int k = n;
		while (i < k) {
			swap(arr, i++, k--);
		}
		return true;
	}

	private static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
}