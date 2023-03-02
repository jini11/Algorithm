import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
	static int V, E;
	static ArrayList<Node>[] adjList;
	
	static class Node{
		int node, weight;

		public Node(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}	
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());

		adjList = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Node(to, weight));
		}
		
		final int INF = Integer.MAX_VALUE;
		int[] distance = new int[V+1];
		PriorityQueue<Node> pqueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
		
		Arrays.fill(distance, INF);
		distance[start] = 0;
		pqueue.add(new Node(start, 0));
		
		while (!pqueue.isEmpty()) {
			Node cur = pqueue.poll();
			
			if (distance[cur.node] < cur.weight) continue;
			
			for (Node next : adjList[cur.node]) {
				int nw = distance[cur.node] + next.weight;
				if (distance[next.node] > nw) {
					distance[next.node] = nw;
					pqueue.add(new Node(next.node, nw));
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			if (distance[i] == INF) {
				sb.append("INF").append("\n");
			} else {
				sb.append(distance[i]).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}