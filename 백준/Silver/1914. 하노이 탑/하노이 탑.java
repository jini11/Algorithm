import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<int[]> answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		answer = new ArrayList<int[]>();
		int n = sc.nextInt();

		if (n <= 20) {
			hanoi(n, 1, 2, 3);
			
			System.out.println(answer.size());
			for (int[] ans : answer) {
				System.out.println(ans[0] + " " + ans[1]);
			}
		} else {
			BigInteger two = new BigInteger("2");
			System.out.println(two.pow(n).subtract(new BigInteger("1")));
		}
	}

	private static void hanoi(int cnt, int from, int mid, int to) {
		if (cnt == 0) {
			return;
		}

		hanoi(cnt - 1, from, to, mid);
		answer.add(new int[] {from, to});
		hanoi(cnt - 1, mid, from, to);
	}

}
