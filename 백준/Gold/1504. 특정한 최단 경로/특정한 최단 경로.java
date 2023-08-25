import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Node implements Comparable<Node>{
        int v, weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int V, E;
    static int[] dist;
    static List<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, weight));
            adjList[to].add(new Node(from, weight));
        }
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 1 -> A -> B -> N
        int[] goA = {1, A, B, V};
        int sumA = 0;
        for (int i = 0; i < 3; i++) {
            int tmp = dijkstra(goA[i], goA[i+1]);
            if (tmp == INF) {
                sumA = INF;
                break;
            }
            sumA += tmp;
        }

        // 1 -> B -> A -> N
        int[] goB = {1, B, A, V};
        int sumB = 0;
        for (int i = 0; i < 3; i++) {
            int tmp = dijkstra(goB[i], goB[i+1]);
            if (tmp == INF) {
                sumB = INF;
                break;
            }
            sumB += tmp;
        }

        int res = Math.min(sumA, sumB);
        if (res == INF) {
            System.out.println(-1);
        } else {
            System.out.println(res);
        }
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        dist = new int[V+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.v == end) break;
            if (dist[cur.v] < cur.weight) continue;

            for (Node next : adjList[cur.v]) {
                if (dist[next.v] > dist[cur.v] + next.weight) {
                    dist[next.v] = dist[cur.v] + next.weight;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }
        return dist[end];
    }
}