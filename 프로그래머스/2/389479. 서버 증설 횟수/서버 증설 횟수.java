import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int cnt = 0; // 현재 서버 수
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        for (int i = 0; i < players.length; i++) {
            // System.out.println(i +"초: ");
            while (!pq.isEmpty()) {
                if (pq.peek()[1] <= i) {
                    int[] remove = pq.poll();
                    cnt -= remove[0];
                } else {
                    break;
                }
            }
            if (players[i] / m > 0) {
                if (players[i] / m > cnt) {
                    int up = players[i] / m - cnt;
                    answer += up;
                    cnt = players[i] / m;
                    pq.add(new int[] {up, i + k});
                    // System.out.println(up +"개 증설");
                }
            }
        
        }
        
        return answer;
    }
}