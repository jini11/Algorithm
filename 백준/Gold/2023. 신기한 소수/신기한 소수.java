import java.util.Scanner;

public class Main {
	static int[] first = {2, 3, 5, 7};
	static int[] other = {1, 3, 5, 7, 9};
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt();
		for (int i = 0; i < first.length; i++) {
			makePrime(1, Integer.toString(first[i]));
		}
		System.out.println(sb.toString());
	}
	
	private static void makePrime(int cnt, String num) {
		if (cnt == N) {
			if (isPrime(Integer.parseInt(num))) {
				sb.append(num + "\n");
			}
			return;
		}
		if (!isPrime(Integer.parseInt(num))) return;
		for (int i = 0; i < other.length; i++) {
			makePrime(cnt + 1, num + other[i]);
		}
	}
	
	private static boolean isPrime(int num) {
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}