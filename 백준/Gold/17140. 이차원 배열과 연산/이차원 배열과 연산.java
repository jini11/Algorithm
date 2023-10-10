import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int rowLen = 3, colLen = 3;
	static int[][] A = new int[100][100];
	static int r, c, k;

	static class Value implements Comparable<Value> {
		int number, cnt;

		public Value(int number, int cnt) {
			this.number = number;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Value o) {
			if (this.cnt == o.cnt) {
				return this.number - o.number;
			}
			return this.cnt - o.cnt;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < rowLen; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < colLen; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(calc());
	}

	private static int calc() {
		for (int i = 0; i <= 100; i++) {
			if (A[r - 1][c - 1] == k) {
				return i;
			}
			operation();
		}
		return -1;
	}

	private static void operation() {
		if (rowLen >= colLen) {
			// R연산
			R();
		} else {
			// C연산
			C();
		}
	}

	private static void R() {
		Map<Integer, Integer> map;
		PriorityQueue<Value> pq;
		
		for (int i = 0; i < rowLen; i++) {
			map = new HashMap<Integer, Integer>();
			pq = new PriorityQueue<Value>();
			for (int j = 0; j < colLen; j++) {
				if (A[i][j] == 0) continue;
				map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
			}
		
			for (Integer key : map.keySet()) {
				pq.add(new Value(key, map.get(key)));
			}
			
			int k = 0;
			while (!pq.isEmpty()) {
				Value v = pq.poll();
				A[i][k++] = v.number;
				A[i][k++] = v.cnt;
			}
			
			colLen = Math.max(colLen, k);
			
			for (int j = k ; j < 100; j++) {
				A[i][j] = 0;
			}
		}
	}

	private static void C() {
		Map<Integer, Integer> map;
		PriorityQueue<Value> pq;
		
		for (int i = 0; i < colLen; i++) {
			map = new HashMap<Integer, Integer>();
			pq = new PriorityQueue<Value>();
			for (int j = 0; j < rowLen; j++) {
				if (A[j][i] == 0) continue;
				map.put(A[j][i], map.getOrDefault(A[j][i], 0) + 1);
			}
		
			for (Integer key : map.keySet()) {
				pq.add(new Value(key, map.get(key)));
			}
			
			int k = 0;
			while (!pq.isEmpty()) {
				Value v = pq.poll();
				A[k++][i] = v.number;
				A[k++][i] = v.cnt;
			}
			
			rowLen = Math.max(rowLen, k);
			
			for (int j = k; j < 100; j++) {
				A[j][i] = 0;
			}
		}
	}
}