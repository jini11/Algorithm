import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Node>[] adjList;
    static int N, M, K, X;

    static class Node implements Comparable<Node> {
        int to, weight;
        public Node(int to, int weight) {
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int i = 0 ; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(to, 1));
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(X, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.weight > dist[cur.to]) continue;

            for (Node next : adjList[cur.to]) {
                if (dist[next.to] > dist[cur.to] + next.weight) {
                    dist[next.to] = dist[cur.to] + next.weight;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                sb.append(i).append("\n");
            }
        }

        if (sb.toString().length() == 0) {
            System.out.println(-1);
        } else {
            System.out.print(sb.toString());
        }
    }
}