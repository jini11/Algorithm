import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static char[] arr;
	static int[] alpha = new int[26];
	static int N, totalCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		arr = input.toCharArray();
		N = arr.length;
		totalCnt = 0;
		for (int i = 0; i < N; i++) {
			alpha[arr[i] - 'a']++;
		}
		per(0, 0);
		System.out.println(totalCnt);
	}

	private static void per(int cnt, int last) {
		if (cnt == N) {
			totalCnt++;
			return;
		}

		for (int i = 0; i < 26; i++) {
			if (alpha[i] > 0 && (cnt == 0 || last != i)) {
				alpha[i]--;
				per(cnt + 1, i);
				alpha[i]++;
			}
		}
	}
}
// 계수 정렬 -> 알파벳 기준