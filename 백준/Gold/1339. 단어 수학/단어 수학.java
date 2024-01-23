import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] alpha = new int[26];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			int temp = (int) Math.pow(10, input.length() - 1);
			for (int j = 0; j < input.length(); j++) {
				alpha[input.charAt(j) - 'A'] += temp;
				temp /= 10;
			}
		}
		
		Arrays.sort(alpha);
		int num = 9;
		int sum = 0;
		for (int i = alpha.length - 1; i >= 0; i--) {
			if (alpha[i] == 0) break;
			sum += (alpha[i] * num--);
		}
		
		System.out.println(sum);
	}
}