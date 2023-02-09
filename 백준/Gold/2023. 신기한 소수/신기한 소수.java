import java.util.Scanner;

public class Main {
	static int[] first = { 2, 3, 5, 7 };
	static int[] other = { 1, 3, 5, 7, 9 };
	static int N;
	static StringBuilder answer; // 신기한 소수들

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		answer = new StringBuilder();
		N = sc.nextInt();
		for (int i = 0; i < first.length; i++) {
			makePrime(1, first[i]);
		}
		System.out.println(answer.toString());
	}

	private static void makePrime(int cnt, int number) {
		if (!isPrime(number)) {		// 소수가 아닐 경우 종료
			return;
		}
		if (cnt == N) {		// N번 뽑았을 경우 종료
			answer.append(number + "\n");
			return;
		}
		for (int i = 0; i < other.length; i++) {
			int nextNumber = number * 10 + other[i];
			makePrime(cnt + 1, nextNumber);
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
