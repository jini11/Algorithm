import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());
        HashMap<String, PriorityQueue<Integer>> map = new HashMap<>();
        long res = 0;
        
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();
            String name = st.nextToken();
            int count = Integer.parseInt(st.nextToken());
            if (command.equals("1")) {
                while (st.hasMoreTokens()) {
                    int value = Integer.parseInt(st.nextToken());
                    if (!map.containsKey(name)) {
                        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
                        pq.add(value);
                        map.put(name, pq);
                    } else {
                        map.get(name).add(value);
                    }
                }
            } else if (command.equals("2")) {
                if (map.get(name) == null) continue;
                while (!map.get(name).isEmpty() && count > 0) {
                    res += map.get(name).poll();
                    count--;
                }
            }
        }
        System.out.println(res);
    }
}