import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int V, start, end;
	static List<Node>[] adjList;
	
	static class Node implements Comparable<Node>{
		int v, weight, max;
		public Node(int v, int weight, int max) {
			this.v = v;
			this.weight = weight;
			this.max = max;
		}
		
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[V + 1];
		for (int i = 1; i < V + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < V - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Node(to, weight, 0));
			adjList[to].add(new Node(from, weight, 0));
		}
		
		System.out.println(dijkstra());
	}
	
	private static int dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int[] dist = new int[V + 1];
		int[] road = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.add(new Node(start, 0, 0));
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.v == end) {
				break;
			}
			if (cur.weight > dist[cur.v]) continue;
			
			for (Node next : adjList[cur.v]) {
				int distance = dist[cur.v] + next.weight;
				if (dist[next.v] > distance) {
					dist[next.v] = distance;
					road[next.v] = Math.max(cur.max, next.weight);
					pq.add(new Node(next.v, dist[next.v], Math.max(next.max, next.weight)));
				}
			}
		}
		
		return dist[end] - road[end];	
	}
}