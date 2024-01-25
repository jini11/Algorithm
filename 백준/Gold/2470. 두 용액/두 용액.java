import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int res = Integer.MAX_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int left = 0;
		int right = N - 1;
		int minLeft = 0;
		int minRight = 0;
		while (left < right) {
			int sum = arr[left] + arr[right];
			if (res > Math.abs(sum)) {
				res = Math.abs(sum);
				minLeft = arr[left];
				minRight = arr[right];
			}
			if (sum < 0) {
				left += 1;
			} else {
				right -= 1;
			}
		}
		
		System.out.println(minLeft + " " + minRight);
	}
}