import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] cost;
    static int[][] dp;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 비용 행렬 입력
        cost = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 배열 초기화
        dp = new int[N][(1 << N)];
        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 0번 도시에서 시작
        System.out.println(tsp(0, 1));
    }

    static int tsp(int current, int visited) {
        // 모든 도시를 방문했을 때
        if(visited == (1 << N) - 1) {
            // 현재 도시에서 시작 도시(0)로 돌아갈 수 있는 경우
            if(cost[current][0] > 0) {
                return cost[current][0];
            }
            return INF;
        }

        // 이미 계산한 경우
        if(dp[current][visited] != -1) {
            return dp[current][visited];
        }

        dp[current][visited] = INF;

        // 방문하지 않은 다음 도시 탐색
        for(int next = 0; next < N; next++) {
            // 이미 방문했거나 갈 수 없는 경우
            if((visited & (1 << next)) != 0 || cost[current][next] == 0) {
                continue;
            }

            // 현재 도시에서 다음 도시까지의 비용 + 다음 도시에서 나머지 도시들을 순회하는 최소 비용
            dp[current][visited] = Math.min(dp[current][visited], 
                                          cost[current][next] + tsp(next, visited | (1 << next)));
        }

        return dp[current][visited];
    }
}