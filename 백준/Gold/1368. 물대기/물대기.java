import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node> {
		int idx, w;
		public Node (int idx, int w) {
			this.idx = idx;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int w = Integer.parseInt(br.readLine());
			pq.add(new Node(i, w));
		}

		
		int[][] adjMatrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int res = 0;
		boolean[] visited = new boolean[N];
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (visited[cur.idx]) continue;
			
			visited[cur.idx] = true;
			res += cur.w;
			
			for (int i = 0; i < N; i++) {
				if (i == cur.idx) continue;
				pq.add(new Node(i, adjMatrix[cur.idx][i]));
			}
		}
		
		System.out.println(res);
	}
}