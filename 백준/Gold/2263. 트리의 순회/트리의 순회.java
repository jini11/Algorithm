import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] in, post, pre;
	static int n, idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		in = new int[n];
		post = new int[n];
		pre = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			post[i] = Integer.parseInt(st.nextToken());
		}
		
		preOrder(0, n - 1, 0, n - 1);
		
		for (int i = 0; i < n; i++) {
			System.out.print(pre[i] + " ");
		}
	}

	private static void preOrder(int is, int ie, int ps, int pe) {
		if (is > ie || ps > pe)
			return;

		pre[idx++] = post[pe];

		int find = is;
		for (int i = is; i <= ie; i++) {
			if (in[i] == post[pe]) {
				break;
			}
			find++;
		}

		preOrder(is, find - 1, ps, ps + find - is - 1);
		preOrder(find + 1, ie, ps + find - is, pe - 1);
	}
}