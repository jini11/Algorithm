import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, d, k, c;
	static int[] sushi, eat;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		sushi = new int[N];
		eat = new int[d + 1];
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < k; i++) {
			if (eat[sushi[i]] == 0) {
				cnt++;
			}
			eat[sushi[i]]++;
		}

		max = cnt;
		for (int i = 1; i < N; i++) {
			if (max <= cnt) {
				if (eat[c] == 0) {
					max = cnt + 1;
				} else {
					max = cnt;
				}
			}
			eat[sushi[i - 1]]--;
			if (eat[sushi[i - 1]] == 0) {
				cnt--;
			}
			if (eat[sushi[(i + k - 1) % N]] == 0) {
				cnt++;
			}
			eat[sushi[(i + k - 1) % N]]++;
		}

		System.out.println(max);
	}
}