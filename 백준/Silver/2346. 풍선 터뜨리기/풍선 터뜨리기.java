import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Deque<int[]> deque = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			deque.add(new int[] { i + 1, Integer.parseInt(st.nextToken()) });
		}

		while (deque.size() > 1) {
			int[] tmp = deque.pollFirst();
			int command = tmp[1];
			sb.append(tmp[0] + " ");
			if (command > 0) {
				for (int i = 1; i < command; i++) {
					deque.addLast(deque.pollFirst());
				}
			} else {
				for (int i = 1; i <= Math.abs(command); i++) {
					deque.addFirst(deque.pollLast());
				}
			}
		}
		sb.append(deque.poll()[0]);
		System.out.println(sb);
	}
}