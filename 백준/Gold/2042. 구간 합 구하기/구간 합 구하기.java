import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] input, tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		input = new long[N];
		tree = new long[N + 1];
		for (int i = 0; i < N; i++) {
			input[i] = Long.parseLong(br.readLine());
		}
		for (int i = 1; i <= N; i++) {
			update(i, input[i - 1]);
		}
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a == 1) { // update
				long dist = c - input[(int) (b - 1)];
				update2(b, dist);
				input[(int) (b - 1)] = c;
			} else { // sum
				sb.append(sum(b, c)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	public static void update(long i, long num) {
		while (i <= N) {
			tree[(int) i] += num;
			i += (i & -i);
		}
	}

	public static void update2(long i, long dist) {
		while (i <= N) {
			tree[(int) i] += dist;
			i += (i & -i);
		}
	}

	public static long sum(long i) {
		long ans = 0;
		while (i > 0) {
			ans += tree[(int) i];
			i -= (i & -i);
		}
		return ans;
	}

	public static long sum(long start, long end) {
		return sum(end) - sum(start - 1);
	}
}