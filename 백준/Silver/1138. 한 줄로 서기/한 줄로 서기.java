import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] tall = new int[n+1];
		List<Integer> answer = new ArrayList<>();
		
		for (int i = 1; i <= n; i++) {
			tall[i] = sc.nextInt();
		}
		
		for (int i = n; i > 0; i--) {
			answer.add(tall[i], i);
		}
		
		for (int ans : answer) {
			System.out.print(ans + " ");
		}
	}

}