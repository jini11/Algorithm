import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int cur = 0;
        Queue<int[]> queue = new ArrayDeque<>(); // 서버 개수, 증설 시각
        
        for (int i = 0; i < players.length; i++) {
            // 필요로 하는 서버 개수
            // 현재 몇 대의 서버가 운영 중인지 확인
            // 서버가 부족하면 증설
            int needed = players[i] / m;
            int size = queue.size();
            for (int[] server : queue) {
                if (server[1] + k <= i) {
                    cur -= queue.poll()[0];
                } else {
                    queue.add(queue.poll());
                }
            }
            int make = needed - cur;
            if (make > 0) {
                cur += make;
                answer += make;
                queue.add(new int[] {make, i});
            }
        }
        
        return answer;
    }
}