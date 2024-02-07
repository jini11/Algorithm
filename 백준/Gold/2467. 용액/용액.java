import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int left = 0;
		int right = N - 1;
		int minSum = Integer.MAX_VALUE;
		int resLeft = 0;
		int resRight = 0;
		while (left < right) {
			int sum = arr[left] + arr[right];
			
			if (minSum >=  Math.abs(sum)) {
				minSum = Math.abs(sum);
				resLeft = left;
				resRight = right;
			}
			if (sum < 0) {
				left++;
			} else if (sum > 0) {
				right--;
			} else {
				break;
			}
		}
		
		System.out.println(arr[resLeft] + " " + arr[resRight]);
	}
}