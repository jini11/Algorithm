import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int F, S, G, U, D;
	static int[] go;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		go = new int[F+1];
		
		System.out.println(bfs());
	}
	
	private static String bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(S);
		go[S] = 1;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (cur == G) {
				return Integer.toString(go[G] - 1);
			}
			
			if (cur + U <= F && go[cur + U] == 0) {
				queue.add(cur + U);
				go[cur + U] = go[cur] + 1;
			}
			if (cur - D >= 1 && go[cur - D] == 0) {
				queue.add(cur - D);
				go[cur - D] = go[cur] + 1;
			}
		}
		return "use the stairs";
	}
}