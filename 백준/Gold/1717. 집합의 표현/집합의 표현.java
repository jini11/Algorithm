import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] parents;

    private static void makeSet() {
        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int v) {
        if (v == parents[v]) {
            return v;
        }

        return parents[v] = findSet(parents[v]);
    }

    private static void union(int u, int v) {
        int uRoot = findSet(u);
        int vRoot = findSet(v);

        if (uRoot < vRoot) {
            int temp = uRoot;
            uRoot = vRoot;
            vRoot = temp;
        }
            
//        int before = parents[uRoot];
//        for (int i = 0; i <= N; i++) {
//            if (parents[i] == before) {
//                parents[i] = vRoot;
//            }
//        }
        parents[uRoot] = vRoot;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        makeSet();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command == 0) {
                union(a, b);
            } else {
                if (findSet(a) == findSet(b)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }

        System.out.println(sb.toString());
    }
}