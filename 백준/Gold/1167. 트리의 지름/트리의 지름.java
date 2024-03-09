import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int V, res, nextNode;
	static List<Node>[] adjList;
	
	static class Node implements Comparable<Node>{
		int v, weight;
		public Node (int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		public int compareTo(Node o) {
			return o.weight - this.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[V + 1];
		for (int i = 1; i < V + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			while (st.hasMoreTokens()) {
				int node = Integer.parseInt(st.nextToken());
				if (node == -1) break;
				int weight = Integer.parseInt(st.nextToken());
				adjList[num].add(new Node(node, weight));
				adjList[node].add(new Node(num, weight));
			}
		}
		
		res = Math.max(res, dijkstra(1));
		res = Math.max(res, dijkstra(nextNode));
		
		System.out.println(res);
	}
	
	private static int dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[V + 1];
		boolean[] visited = new boolean[V + 1];
		dist[start] = 0;
		pq.add(new Node(start, 0));
		int max = 0; 
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (visited[cur.v]) continue;
			
			visited[cur.v] = true;
			
			for (Node next : adjList[cur.v]) {
				int distance = next.weight + dist[cur.v];
				if (!visited[next.v] && distance > dist[next.v]) {
					dist[next.v] = distance;
					pq.add(new Node(next.v, distance));
					if (max < distance) {
						max = distance;
						nextNode = next.v;
					}
				}
			}
		}
		
		return max;
	}
}