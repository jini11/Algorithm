import java.util.Scanner;

public class Main {
	static int k, size;
	static StringBuilder[] ans;
	static int[] node;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		k = sc.nextInt();
		size = (int) Math.pow(2, k) - 1;
		node = new int[size];

		for (int i = 0; i < size; i++) {
			node[i] = sc.nextInt();
		}

		ans = new StringBuilder[k];
		for (int i = 0; i < k; i++) {
			ans[i] = new StringBuilder();
		}

		go(0, size, 0);

		for (int i = 0; i < k; i++) {
			System.out.println(ans[i].toString());
		}
	}

	private static void go(int start, int end, int depth) {
		if (depth == k) {
			return;
		}

		int mid = (start + end) / 2;
		ans[depth].append(node[mid] + " ");

		go(start, mid - 1, depth + 1);
		go(mid + 1, end, depth + 1);
	}
}