import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, C, ans;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int left = 1; // 최소 간격
		int right = arr[N-1] - arr[0]; // 최대 간격
		
		while (left <= right) {
			int mid = (left + right) / 2; // 집 사이 간격
			int start = arr[0]; // 공유기가 설치된 집
			int cnt = 1; // 공유기 수
			
			for (int i = 1; i < N; i++) {
				int distance = arr[i] - start;
				if (distance >= mid) { // 공유기 설치 가능
					cnt++;
					start = arr[i];
				}
			}
			
			if (cnt >= C) { // 최소거리가 작기 때문에 늘려줘야 함
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(ans);
	}
}