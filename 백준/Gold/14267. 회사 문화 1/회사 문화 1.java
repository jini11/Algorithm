import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] dist;
	static List<Node>[] adjList;
	
	static class Node{
		int v, weight;
		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
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
		
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for (int i = 2; i < N + 1; i++) {
			int top = Integer.parseInt(st.nextToken());
			adjList[top].add(new Node(i, 0));
		}
		
		dist = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			dist[start] += weight;
		}
		
		dfs(1);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < N + 1; i++) {
			sb.append(dist[i] + " ");
		}
		System.out.println(sb.toString());
	}
	
	private static void dfs(int node) {
		for (Node next : adjList[node]) {
			dist[next.v] += dist[node];
			dfs(next.v);
		}
	}
}