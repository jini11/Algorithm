import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		for (int i = 1; i <= n; i++) {
			for (int j = i; j < n; j++) {
				System.out.print(" ");
			}
			for (int j = 1; j < 2*i; j++) {
				if (j % 2 == 1) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

}