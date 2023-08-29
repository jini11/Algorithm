import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int left = 0;
		int right = arr.length - 1;
		int minLeft = arr[left];
		int minRight = arr[right];
		int minSum = Integer.MAX_VALUE;

		while (left < right) {
			int sum = arr[left] + arr[right];
			if (Math.abs(minSum) > Math.abs(sum)) {
				minSum = sum;
				minLeft = arr[left];
				minRight = arr[right];
			}

			if (sum > 0) {
				right--;
			} else {
				left++;
			}
		}

		System.out.println(minLeft + " " + minRight);
	}
}