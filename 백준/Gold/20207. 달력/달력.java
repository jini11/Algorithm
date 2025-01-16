import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Date implements Comparable<Date>{
        int s, e;
        public Date(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Date o) {
            if (this.s == o.s) {
                return (o.e - o.s) - (this.e - this.s);
            }
            return this.s - o.s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Date> pq = new PriorityQueue<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Date(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int ans = 0;
        int start = pq.peek().s;
        int end = pq.poll().e;
        int cnt = 1;
        List<Integer> endLayer = new ArrayList<>();
        endLayer.add(end);
        while (!pq.isEmpty()) {
            if (end + 1 >= pq.peek().s) {
                boolean canAttach = false;
                Collections.sort(endLayer);
                for (int i = 0; i < endLayer.size(); i++) {
                    if (endLayer.get(i) < pq.peek().s) {
                        endLayer.remove(i);
                        endLayer.add(pq.poll().e);
                        canAttach = true;
                        break;
                    }
                }
                if (!canAttach) {
                    cnt = endLayer.size() + 1;
                    endLayer.add(pq.poll().e);
                }
                end = Math.max(end, endLayer.get(endLayer.size() - 1));
            } else {
                ans += (end - start + 1) * cnt;
                cnt = 1;
                endLayer = new ArrayList<>();
                endLayer.add(pq.peek().e);
                start = pq.peek().s;
                end = pq.poll().e;
            }
        }
        ans += (end - start + 1) * cnt;
        System.out.println(ans);
    }
}