import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L, R, time;
    static boolean[][] visited;
    static int[][] map;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            boolean isContinue = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    if (bfs(i, j)) {
                        isContinue = true;
                    }
                }
            }
            if (!isContinue) break;
            time++;
        }

        System.out.println(time);
    }

    private static boolean bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> list = new ArrayList<>();
        list.add(new int[] {r, c});
        visited[r][c] = true;
        int peopleCnt = map[r][c];
        queue.add(new int[] {r, c}); // r, c, 칸의 개수, 인구수

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (isOut(nr, nc) || visited[nr][nc]) continue;
                int diff = Math.abs(map[cur[0]][cur[1]] - map[nr][nc]);
                if (diff >= L && diff <= R) {
                    peopleCnt += map[nr][nc];
                    list.add(new int[] {nr, nc});
                    queue.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        if (list.size() > 1) {
            int movedCnt = peopleCnt / list.size();
            for (int[] tmp : list) {
                map[tmp[0]][tmp[1]] = movedCnt;
            }
            return true;
        }
        return false;
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= N;
    }
}