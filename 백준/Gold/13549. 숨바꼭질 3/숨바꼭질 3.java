import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[100001];
		int res = 0;
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(N);
		arr[N] = 1;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (cur == K) {
				res = arr[cur] - 1;
				break;
			}
			
			if (cur * 2 <= 100000 && arr[cur * 2] == 0) {
				arr[cur * 2] += arr[cur];
				queue.add(cur * 2);
			}
			if (cur - 1 >= 0 && arr[cur - 1] == 0) {
				arr[cur - 1] += arr[cur] + 1;
				queue.add(cur - 1);
			}
			if (cur + 1 <= 100000 && arr[cur + 1] == 0) {
				arr[cur + 1] += arr[cur] + 1;
				queue.add(cur + 1);
			}
		}
		System.out.println(res);
	}
}