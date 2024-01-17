import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int res = Integer.MAX_VALUE;
		int cnt = 0;
		
		int[] upStone = new int[N/2];
		int[] downStone = new int[N/2];
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				downStone[i / 2] = Integer.parseInt(br.readLine());
			} else {
				upStone[i / 2] = Integer.parseInt(br.readLine());
			}
		}
		
		Arrays.sort(upStone);
		Arrays.sort(downStone);
		
		for (int height = 1; height < H + 1; height++) {
			int obstacle = binarySearch(upStone, H - height + 1) + binarySearch(downStone, height);
			if (res > obstacle) {
				res = obstacle;
				cnt = 1;
			} else if (res == obstacle) {
				cnt++;
			}
		}
		
		System.out.println(res + " " + cnt);
	}
	
	private static int binarySearch(int[] arr, int height) {
		int left = 0;
		int right = arr.length;
	
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] < height) {
				left = mid + 1;
			} else if (arr[mid] >= height) {
				right = mid;
			}
		}
		return arr.length - right;
	}
}