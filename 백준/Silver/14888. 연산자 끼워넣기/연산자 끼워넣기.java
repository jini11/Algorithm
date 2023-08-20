import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, min, max;
	static final int[] op = new int[4];
	static int[] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		num = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}

		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;

		dfs(num[0], 1);

		System.out.println(max);
		System.out.println(min);
	}

	private static void dfs(int sum, int depth) {
		if (depth == N) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] == 0) continue;
			op[i]--;
			dfs(calc(sum, depth, i), depth + 1);
			op[i]++;
		}
	}

	private static int calc(int sum, int idx, int op) {
		switch (op) {
			case 0:
				sum += num[idx];
				break;
			case 1:
				sum -= num[idx];
				break;
			case 2:
				sum *= num[idx];
				break;
			case 3:
				if (sum < 0) {
					sum = Math.abs(sum) / num[idx];
					sum *= -1;
				} else {
					sum /= num[idx];
				}
				break;
		}

		return sum;
	}
}