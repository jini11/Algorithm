import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, root, leaf;
    static List<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == -1) {
                root = i;
            } else {
                tree[num].add(i);
            }
        }
        int start = Integer.parseInt(br.readLine());
        if (start == root) {
            System.out.println(0);
            return;
        }

        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (tree[i].contains(start)) {
                tree[i].remove(Integer.valueOf(start));
            }
        }

        dfs(root);
        System.out.println(leaf);
    }

    private static void dfs (int cur) {
        if (tree[cur].size() == 0) {
            leaf++;
            return;
        }
        visited[cur] = true;

        for (int i = 0; i < tree[cur].size(); i++) {
            if (!visited[tree[cur].get(i)]) {
                dfs(tree[cur].get(i));
            }
        }
    }
}
