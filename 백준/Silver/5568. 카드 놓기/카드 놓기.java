import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	
	static int N, K;
	static int[] arr, numbers;
	static boolean[] isSelected;
	static Set<String> set;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		numbers = new int[K];
		isSelected = new boolean[N];
		set = new HashSet<String>();
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		perm(0);
		
		System.out.println(set.size());
	}
	
	private static void perm(int cnt) {
		if (cnt == K) {
			StringBuilder sb = new StringBuilder();
			for (int number : numbers) {
				sb.append(number);
			}
			set.add(sb.toString());
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) continue;
			numbers[cnt] = arr[i];
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}
}