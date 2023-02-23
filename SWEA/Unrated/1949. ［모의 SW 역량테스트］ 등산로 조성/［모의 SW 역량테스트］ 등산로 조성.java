import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static int res = 0;

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            visited = new boolean[N][N];
            res = 0;
            
            int top = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    top = Math.max(top, map[i][j]);
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == top) {
                    	visited[i][j] = true;
                        dfs(i, j, 1, 1);
                        visited[i][j] = false;
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(res).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int row, int col, int chance, int cnt) {
    	res = Math.max(res, cnt);
    	
        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
                continue;
            
            visited[nr][nc] = true;
            if (map[row][col] > map[nr][nc]) {
                dfs(nr, nc, chance, cnt + 1);
            } else {
                if (map[nr][nc] - K < map[row][col] && chance == 1) {
                	int curHeight = map[nr][nc];
                	map[nr][nc] = map[row][col] - 1;
                    dfs(nr, nc, 0, cnt + 1);
                    map[nr][nc] = curHeight;
                }
            }
            visited[nr][nc] = false;
        }
    }
}