import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, cnt;
	static List<Node>[] adjList;
	
	static class Node implements Comparable<Node> {
		int v, weight;
		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Node(to, weight));
			adjList[to].add(new Node(from, weight));
		}
		
		dijkstra(1);
		
		System.out.println(cnt);
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (dist[cur.v] < cur.weight) continue;
			
			for (Node next : adjList[cur.v]) {
				int distance = dist[cur.v] + next.weight;
				if (distance < dist[next.v]) {
					dist[next.v] = distance;
					pq.add(new Node(next.v, distance));
				}
			}
		}
		cnt = dist[N];
	}
}