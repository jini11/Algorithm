import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr, frontSum, backSum;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		frontSum = new int[N];
		backSum = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		int sum = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
			frontSum[i] = sum;
		}

		sum = 0;
		for (int i = N - 1; i >= 0; i--) {
			sum += arr[i];
			backSum[i] = sum;
		}

		getHoney1();
		getHoney2();
		getHoney3();

		System.out.println(res);
	}

	private static void getHoney1() {
		int bee1 = 0;
		int bee2 = N - 1;

		for (int beehive = 1; beehive < N - 1; beehive++) {
			int sumBee1 = frontSum[beehive] - frontSum[bee1];
			int sumBee2 = backSum[beehive] - backSum[bee2];
			res = Math.max(res, sumBee1 + sumBee2);
		}
	}

	private static void getHoney2() {
		int bee1 = 0;
		int beehive = N - 1;

		for (int bee2 = 1; bee2 < N - 1; bee2++) {
			int sumBee1 = frontSum[beehive] - arr[bee1] - arr[bee2];
			int sumBee2 = frontSum[beehive] - frontSum[bee2];
			res = Math.max(res, sumBee1 + sumBee2);
		}
	}
	
	private static void getHoney3() {
		int beehive = 0;
		int bee2 = N-1;
		
		for (int bee1 = 1; bee1 < N-1; bee1++) {
			int sumBee1 = backSum[beehive] - backSum[bee1];
			int sumBee2 = backSum[beehive] - arr[bee1] - arr[bee2];
			res = Math.max(res, sumBee1 + sumBee2);
		}
	}
}