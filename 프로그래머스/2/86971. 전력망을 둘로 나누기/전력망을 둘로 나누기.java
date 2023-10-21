import java.util.*;

class Solution {
    static boolean[][] map;
    static boolean[] visited;
    static int N, sum1, sum2;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        N = n;
        map = new boolean[N+1][N+1];
        for (int i = 0; i < wires.length; i++) {
            int from = wires[i][0];
            int to = wires[i][1];
            map[from][to] = true;
            map[to][from] = true;
        }
        
        for (int k = 0; k < wires.length; k++) {
            int from = wires[k][0];
            int to = wires[k][1];
            map[from][to] = false;
            map[to][from] = false;
            visited = new boolean[N+1];
            sum1 = 0;
            sum2 = 0;
            boolean flag = false;
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    bfs(i, flag);
                    flag = true;
                }
            }
            
            map[from][to] = true;
            map[to][from] = true;
            
            answer = Math.min(answer, Math.abs(sum1 - sum2));
        }
        return answer;
    }
    
    private static void bfs(int start, boolean flag) {
        Queue<Integer> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.add(start);
        
        int sum = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && map[cur][i]) {
                    visited[i] = true;
                    sum++;
                    queue.add(i);
                }
            }
        }
        if (flag) {
            sum1 = sum;
        } else {
            sum2 = sum;
        }
    }
}

// for문 돌면서 wires 하나씩 자르기
// 두 그룹 각각 노드 개수 구하기
// 차이 구해서 최소값 갱신
// 복구