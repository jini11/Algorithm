import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] adjList;
    static int[] res;
    static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[V + 1];
        res = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }

        bfs(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(res[i] + "\n");
        }
        System.out.println(sb.toString());
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[V + 1];

        queue.offer(start);
        visited[start] = true;
        int cnt = 1;

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            res[cur] = cnt++;
            Collections.sort(adjList[cur], Collections.reverseOrder());
            
            for (int vertex : adjList[cur]) {
                if (!visited[vertex]) {
                    queue.offer(vertex);
                    visited[vertex] = true;
                }
            }
        }
    }
}