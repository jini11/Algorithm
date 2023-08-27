import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E;

    static class Node implements Comparable<Node> {
        int from, to, weight;
        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        List<Node>[] adjList = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(from, to, weight));
            adjList[to].add(new Node(to, from, weight));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V + 1];

        int cnt = 1;
        int result = 0;

        pq.addAll(adjList[1]);
        visited[1] = true;

        while (cnt != V) {
            Node cur = pq.poll();
            if (visited[cur.to]) {
                continue;
            }
            result += cur.weight;
            visited[cur.to] = true;
            pq.addAll(adjList[cur.to]);
            cnt++;
        }

        System.out.println(result);
    }
}