import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int size = 1;
		int count = 0;
		
		
		while (size < K) {
			size = size * 2;
		}
		
		System.out.print(size + " ");
		
		while (K > 0) {
			if (K >= size) {
				K -= size;
			} else {
				size /= 2;
				count++;
			}
		}
		System.out.println(count);
	}
}