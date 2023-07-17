import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, res;
	static ArrayList<Integer>[] adjList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		dfs(1, 0, new boolean[N+1]);
		
		if (res % 2 != 0) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
	
	private static void dfs(int cur, int cnt, boolean[] visited) {
		visited[cur] = true;
		
		for (int v : adjList[cur]) {
			if (!visited[v]) {
				dfs(v, cnt + 1, visited);
			}
		}
		
		if (cur != 1 && adjList[cur].size() == 1) {
			res += cnt;
		}
	}
}