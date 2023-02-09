import java.util.Scanner;

public class Main {
	static int N;
	static int totalCnt;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		hanoi(0, 1, 2, 3);
		System.out.println(totalCnt - 1);
		System.out.println(sb);
	}

	private static void hanoi(int cnt, int from, int other, int to) {
		if (cnt == N) {
			totalCnt++;
			return;
		}

		hanoi(cnt + 1, from, to, other);
		sb.append(from + " " + to + "\n");
		hanoi(cnt + 1, other, from, to);

	}
}