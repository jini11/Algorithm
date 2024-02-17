import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static Node[] node;
	
	static class Node {
		int from, to;
		public Node(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			if (N == 0 && K == 0) {
				break;
			}
			
			int fromIdx = -1;
			node = new Node[N];
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			node[0] = new Node(-1, num++);
			int kIdx = 0;
			
			for (int i = 1; i < N; i++) {
				int to = Integer.parseInt(st.nextToken());
				if (num != to) {
					num = to;
					fromIdx++;
				}
				if (K == to) {
					kIdx = i;
				}
				node[i] = new Node(fromIdx, to);
				num++;
			}
			
			int res = 0;
			for (int i = 0; i < N; i++) {
				if (node[kIdx].from == -1) break;
				if (node[i].from == -1) continue;
				if (node[node[kIdx].from].from == node[node[i].from].from && node[kIdx].from != node[i].from) {
					res++;
				}
			}
			
			sb.append(res).append("\n");
		}
		System.out.println(sb.toString());
	}
}