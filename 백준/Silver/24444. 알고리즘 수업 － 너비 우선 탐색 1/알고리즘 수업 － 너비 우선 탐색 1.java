import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 정점의 수
        int m = sc.nextInt(); // 간선의 수
        int r = sc.nextInt(); // 시작지점

        ArrayList<Integer>[] arr = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        while (m-- >0) {
            int u = sc.nextInt();
            int w = sc.nextInt();
            arr[u].add(w);
            arr[w].add(u);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(arr[i]);
        }

        int[] check = new int[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(r);
        int depth = 1;
        check[r] = depth;
        while(!q.isEmpty()) {
            int x = q.poll();
            for (int i = 0; i < arr[x].size(); i++) {
                int h = arr[x].get(i);
                if (check[h] == 0) {
                    depth += 1;
                    check[h] = depth;
                    q.add(h);
                }
            }
        }

        for (int i = 1; i < check.length; i++) {
            System.out.println(check[i]);
        }
    }
}