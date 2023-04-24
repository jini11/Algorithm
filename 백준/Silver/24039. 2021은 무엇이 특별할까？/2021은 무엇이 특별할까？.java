import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int result = 0, i, j;
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (i = 2; i <= 10000; i++) {
			for (j = 2; j < i; j++) {
				if (i % j == 0) {
					break;
				}
			}

			if (i == j) {
				list.add(i);
			}
		}

		
		for (i = 0; i < list.size(); i++) {
			result = list.get(i) * list.get(i + 1);
			if (result > n) {
				System.out.println(result);
				System.exit(0);
			}
		}
	}
}