import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr, min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		min = new int[N * 4];
		initMin(1, N, 1);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			sb.append(findMin(1, N, 1, left, right) + "\n");
		}
		System.out.println(sb.toString());
	}

	private static int initMin(int start, int end, int node) {
		if (start == end) {
			return min[node] = arr[start];
		}

		int mid = (start + end) / 2;
		return min[node] = Math.min(initMin(start, mid, node * 2), initMin(mid + 1, end, node * 2 + 1));
	}

	private static int findMin(int start, int end, int node, int left, int right) {
		if (right < start || end < left) {
			return Integer.MAX_VALUE;
		}

		if (start >= left && end <= right) {
			return min[node];
		}

		int mid = (start + end) / 2;
		return Math.min(findMin(start, mid, node * 2, left, right), findMin(mid + 1, end, node * 2 + 1, left, right));
	}
}