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
		
		int max = 0;
		int left = 0;
		int right = N - 1;
		
		while (left <= right) {
			int sum = (right - left - 1) * Math.min(arr[left], arr[right]);
			max = Math.max(max, sum);
			
			if (arr[left] < arr[right]) {
				left++;
			} else {
				right--;
			}
		}
		System.out.println(max);
	}
}